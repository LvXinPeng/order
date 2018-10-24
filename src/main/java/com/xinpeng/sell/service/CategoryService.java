package com.xinpeng.sell.service;

import com.xinpeng.sell.dataObject.ProductCategory;

import java.util.List;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 14:02
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
