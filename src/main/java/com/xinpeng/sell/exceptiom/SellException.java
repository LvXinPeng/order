package com.xinpeng.sell.exceptiom;

import com.xinpeng.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author 吕新鹏
 * @Date 2018/6/19 13:51
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
