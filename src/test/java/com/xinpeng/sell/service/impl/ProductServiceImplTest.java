package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.dataObject.ProductInfo;
import com.xinpeng.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl service;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = service.findOne("123");
        Assert.assertEquals("123",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = service.findUpAll();
        assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = service.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("124");
        productInfo.setProductName("芭比娃娃");
        productInfo.setProductPrice(new BigDecimal(88));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("很好看");
        productInfo.setProductIcon("xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(1);

        ProductInfo result = service.save(productInfo);
        assertNotNull(result);
    }

}