package com.xinpeng.sell.service;

import com.xinpeng.sell.dataObject.SellerInfo;

/**
 * @Author 吕新鹏
 * @Date 2018/7/3 11:11
 */
public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
