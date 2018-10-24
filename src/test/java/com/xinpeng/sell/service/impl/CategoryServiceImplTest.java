package com.xinpeng.sell.service.impl;

import com.xinpeng.sell.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/15 14:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategories = categoryService.findAll();
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(Arrays.asList(1,2));
        Assert.assertNotEquals(0,productCategories.size());

    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("男生专项",10);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

}