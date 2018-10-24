package com.xinpeng.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http返回请求的最外层对象
 *
 * @Author 吕新鹏
 * @Date 2018/6/18 10:59
 * VO（View Object）：视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 7340802499935647451L;
    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //内容
    private T data;
}
