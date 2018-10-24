package com.xinpeng.sell.dto;

import lombok.Data;

/**
 * 购物车
 *
 * @Author 吕新鹏
 * @Date 2018/6/19 17:06
 * 数据传输对象（DTO)(Data Transfer Object) 用于【展示层与服务层】之间的数据传输对象。
 */
@Data
public class CarDto {

    private String productId;

    private Integer productQuantity;

    public CarDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
