package com.xinpeng.sell.controller;

import com.xinpeng.sell.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 吕新鹏
 * @Date 2018/7/4 22:51
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SeckullController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 查询秒杀活动特价商品的信息
     *
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")//PathVariable注解,设置路径中的值到变量
    public String query(@PathVariable String productId) throws Exception {
        return seckillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到返回"很抱歉,****",抢到了会返回剩余的库存量
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")//PathVariable注解,设置路径中的值到变量
    public String skill(@PathVariable String productId) throws Exception {
        log.info("@skill request,productId={}", productId);
        seckillService.orderProductMocckDiffUser(productId);
        return seckillService.querySecKillProductInfo(productId);
    }

}
