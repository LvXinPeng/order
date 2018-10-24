package com.xinpeng.sell.enums;

import lombok.Getter;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 16:05
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
