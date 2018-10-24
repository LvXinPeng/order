package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.dataObject.SellerInfo;
import com.xinpeng.sell.repository.SellerInfoRepository;
import com.xinpeng.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 吕新鹏
 * @Date 2018/7/3 11:12
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
