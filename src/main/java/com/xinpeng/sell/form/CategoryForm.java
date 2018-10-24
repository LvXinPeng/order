package com.xinpeng.sell.form;

import lombok.Data;

/**
 * @Author 吕新鹏
 * @Date 2018/7/2 17:50
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
