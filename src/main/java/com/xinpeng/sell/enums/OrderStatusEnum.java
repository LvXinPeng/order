package com.xinpeng.sell.enums;

import lombok.Getter;

/**
 * @Author 吕新鹏
 * @Date 2018/6/18 14:40
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
