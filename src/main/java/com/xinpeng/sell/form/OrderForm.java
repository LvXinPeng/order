package com.xinpeng.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author 吕新鹏
 * @Date 2018/6/21 16:48
 */
@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;

    //买家微信id
    @NotEmpty(message = "openid必填")
    private String openid;

    //购物车
    @NotEmpty(message = "item必填")
    private String items;

}
