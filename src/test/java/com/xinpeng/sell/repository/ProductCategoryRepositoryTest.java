package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @Author 吕新鹏
 * @Date 2018/6/15 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findTest(){
        Optional<ProductCategory> productCategory = repository.findById(2);
        System.out.println(productCategory.toString());
    }
    @Test
    public void save(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("哈哈");
        productCategory.setCategoryType(2);
        repository.save(productCategory);
    }
    @Test
    public void findAll(){
        ProductCategory productCategory = repository.findById(2).get();
        productCategory.setCategoryName("pengpeng");
        productCategory.setCategoryType(1);
        repository.save(productCategory);

    }
   @Test
    public void findByCategoryTypeIn(){
       List<Integer> list = Arrays.asList(1,2);
       List<ProductCategory> result = repository.findByCategoryTypeIn(list);
       Assert.assertNotEquals(0,result.size());
   }
}