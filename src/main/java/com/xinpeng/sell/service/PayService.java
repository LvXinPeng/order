package com.xinpeng.sell.service;

import com.xinpeng.sell.dto.OrderDto;

/**
 * @Author 吕新鹏
 * @Date 2018/6/27 16:13
 */
public interface PayService {
    void create(OrderDto orderDto);
}
