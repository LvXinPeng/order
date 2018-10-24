package com.xinpeng.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 吕新鹏
 * @Date 2018/6/14 20:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test() {
        String a = "hello";
        String b = "world";
        log.info("{},{}", a, b);
        log.info("info-----");
        log.error("error----");
    }
}
