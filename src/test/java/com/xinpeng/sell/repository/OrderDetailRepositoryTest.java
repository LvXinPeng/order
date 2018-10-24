package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/19 12:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("123");
        orderDetail.setProductName("充气娃娃");
        orderDetail.setProductPrice(new BigDecimal(333.3));
        orderDetail.setProductQuantity(100);
        orderDetail.setProductIcon("xxx.jpg");

        OrderDetail result = repository.save(orderDetail);
        assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> result = repository.findByOrderId("123");
        assertNotEquals(0,result.size());
    }

}