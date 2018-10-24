package com.xinpeng.sell.dataObject.mapper;

import com.xinpeng.sell.dataObject.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author 吕新鹏
 * @Date 2018/7/4 16:59
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=VARCHAR})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")

    })
    ProductCategory findByCategoryType(Integer categoryType);

}
