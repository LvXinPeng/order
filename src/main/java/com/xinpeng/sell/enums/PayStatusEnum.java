package com.xinpeng.sell.enums;

import lombok.Getter;

/**
 * @Author 吕新鹏
 * @Date 2018/6/18 14:44
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
