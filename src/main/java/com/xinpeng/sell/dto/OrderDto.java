package com.xinpeng.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xinpeng.sell.dataObject.OrderDetail;
import com.xinpeng.sell.enums.OrderStatusEnum;
import com.xinpeng.sell.enums.PayStatusEnum;
import com.xinpeng.sell.utils.EnumUtil;
import com.xinpeng.sell.utils.serializer.DateSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * 数据传输对象（DTO)(Data Transfer Object) 用于【展示层与服务层】之间的数据传输对象。
 * @Author 吕新鹏
 * @Date 2018/6/19 13:14
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    @JsonSerialize(using = DateSerializer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = DateSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
