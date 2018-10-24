package com.xinpeng.sell.controller;

import com.xinpeng.sell.VO.ResultVO;
import com.xinpeng.sell.converter.OrderForm2OrderDtoConverter;
import com.xinpeng.sell.dto.OrderDto;
import com.xinpeng.sell.enums.ResultEnum;
import com.xinpeng.sell.exceptiom.SellException;
import com.xinpeng.sell.form.OrderForm;
import com.xinpeng.sell.service.OrderService;
import com.xinpeng.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 吕新鹏
 * @Date 2018/6/21 16:45
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单 参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto createResult = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @RequestMapping("/list")
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, request);
        return ResultVOUtil.success(orderDtoPage.getContent());

    }

    //订单详情
    @RequestMapping("/detail")
    public ResultVO<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO
        OrderDto orderDto = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDto);
    }

    //取消订单
    @RequestMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        //todo
        OrderDto orderDto = orderService.findOne(orderId);
        orderService.cancel(orderDto);

        return ResultVOUtil.success();
    }

}
