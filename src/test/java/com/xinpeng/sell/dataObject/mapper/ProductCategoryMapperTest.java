package com.xinpeng.sell.dataObject.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/7/4 17:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName", "帅哥最爱");
        map.put("categoryType", 101);
        int result = mapper.insertByMap(map);
        assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception {
    }

    @Test
    public void findByCategoryType() throws Exception {
    }

}