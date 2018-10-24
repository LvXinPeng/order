package com.xinpeng.sell.service;

/**
 * @Author 吕新鹏
 * @Date 2018/7/4 22:49
 */
public interface SeckillService {
    /**
     * 查询特价商品
     *
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 秒杀的逻辑方法
     *
     * @param productId
     */
    void orderProductMocckDiffUser(String productId);

}
