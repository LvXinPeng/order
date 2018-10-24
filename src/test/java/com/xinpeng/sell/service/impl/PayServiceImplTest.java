package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.dto.OrderDto;
import com.xinpeng.sell.service.OrderService;
import com.xinpeng.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/27 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDto = orderService.findOne("152966618002065429");
        payService.create(orderDto);
    }

}