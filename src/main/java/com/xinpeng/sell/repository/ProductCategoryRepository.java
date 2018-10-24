package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 11:11
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
