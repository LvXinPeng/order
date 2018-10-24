package com.xinpeng.sell.service;

import com.xinpeng.sell.dto.OrderDto;

/**
 * @Author 吕新鹏
 * @Date 2018/6/22 21:02
 */
public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openid, String orderId);

    //取消订单
    OrderDto cancelOrder(String openid, String orderId);

}
