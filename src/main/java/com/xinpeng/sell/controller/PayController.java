package com.xinpeng.sell.controller;

import com.xinpeng.sell.dto.OrderDto;
import com.xinpeng.sell.enums.ResultEnum;
import com.xinpeng.sell.exceptiom.SellException;
import com.xinpeng.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 吕新鹏
 * @Date 2018/6/27 16:07
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        //1.查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.发起支付

    }

}
