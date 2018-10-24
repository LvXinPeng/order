package com.xinpeng.sell.utils;

import com.xinpeng.sell.enums.CodeEnum;

/**
 * @Author 吕新鹏
 * @Date 2018/7/2 12:32
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
