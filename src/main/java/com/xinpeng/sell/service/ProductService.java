package com.xinpeng.sell.service;

import com.xinpeng.sell.dataObject.ProductInfo;
import com.xinpeng.sell.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 15:48
 */
public interface ProductService {

    ProductInfo findOne(String productInfoId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    //List<ProductInfo> findByProductStatus(Integer productInStatus);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CarDto> carDtoList);

    //减库存
    void decreaseStock(List<CarDto> carDtoList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
