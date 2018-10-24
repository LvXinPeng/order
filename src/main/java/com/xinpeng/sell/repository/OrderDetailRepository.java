package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 吕新鹏
 * @Date 2018/6/18 22:27
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
