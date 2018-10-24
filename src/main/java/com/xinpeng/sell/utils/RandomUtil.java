package com.xinpeng.sell.utils;

import java.util.Random;

/**
 * @Author 吕新鹏
 * @Date 2018/6/19 14:47
 */
public class RandomUtil {
public static synchronized String getUniqueKey(){
    Random random = new Random();
    Integer num = random.nextInt(90000) + 10000;
    return System.currentTimeMillis() + String.valueOf(num);
}
}
