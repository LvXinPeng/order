package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 吕新鹏
 * @Date 2018/7/3 10:59
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
