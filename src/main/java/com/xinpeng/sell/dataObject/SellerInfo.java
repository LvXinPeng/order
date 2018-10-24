package com.xinpeng.sell.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author 吕新鹏
 * @Date 2018/7/3 10:58
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
