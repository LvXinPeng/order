package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.dataObject.OrderDetail;
import com.xinpeng.sell.dto.OrderDto;
import com.xinpeng.sell.enums.OrderStatusEnum;
import com.xinpeng.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/19 19:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "147258";

    private final String ORDER_ID = "152941226578129929";


    @Test
    public void create() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("新鹏");
        orderDto.setBuyerAddress("青岛科技大学");
        orderDto.setBuyerPhone("123456789012");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("124");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDto.setOrderDetailList(orderDetailList);

        OrderDto result = orderService.create(orderDto);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDto result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result={}",result);
        assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYER_OPENID, request);
        assertNotEquals(0,orderDtoPage.getTotalElements());

    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(orderDto);
        assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDto);
        assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDto);
        assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getOrderStatus());
    }

}