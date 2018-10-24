package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.VO.ProductVO;
import com.xinpeng.sell.converter.OrderMaster2OrderDTOConverter;
import com.xinpeng.sell.dataObject.OrderDetail;
import com.xinpeng.sell.dataObject.OrderMaster;
import com.xinpeng.sell.dataObject.ProductInfo;
import com.xinpeng.sell.dto.CarDto;
import com.xinpeng.sell.dto.OrderDto;
import com.xinpeng.sell.enums.OrderStatusEnum;
import com.xinpeng.sell.enums.PayStatusEnum;
import com.xinpeng.sell.enums.ResultEnum;
import com.xinpeng.sell.exceptiom.SellException;
import com.xinpeng.sell.repository.OrderDetailRepository;
import com.xinpeng.sell.repository.OrderMasterRepository;
import com.xinpeng.sell.service.OrderService;
import com.xinpeng.sell.service.ProductService;
import com.xinpeng.sell.service.WebSocket;
import com.xinpeng.sell.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author 吕新鹏
 * @Date 2018/6/19 13:20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    WebSocket webSocket;

    private String orderId = RandomUtil.getUniqueKey();

    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        //1.查询商品（数量、价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
            }

            //2. 计算总价
            orderAmount = productInfo.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(RandomUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库orderDetail和orderMaster）
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CarDto> carDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CarDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(carDtoList);


        //发送websocket消息
        webSocket.sendMessage("有新的订单" + orderDto.getOrderId());
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDto>(orderDtoList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】 订单状态不正确，orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);

        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】 订单中无商品详情 orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CarDto> carDtoList = orderDto.getOrderDetailList().stream().map(e -> new CarDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(carDtoList);


        //如果以支付，需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO

        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】 订单状态不正确,orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if (orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付成功】 订单状态不正确,orderId={},orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】,订单支付转台不正确.orderDto={]", orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
    }
}
