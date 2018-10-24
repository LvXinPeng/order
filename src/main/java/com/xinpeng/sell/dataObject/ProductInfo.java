package com.xinpeng.sell.dataObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xinpeng.sell.enums.PayStatusEnum;
import com.xinpeng.sell.enums.ProductStatusEnum;
import com.xinpeng.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 15:22
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    //商品名字
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //商品小图
    private String productIcon;

    //状态 0正常 1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //类目编号
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
