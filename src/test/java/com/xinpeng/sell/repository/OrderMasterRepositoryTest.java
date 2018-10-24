package com.xinpeng.sell.repository;

import com.xinpeng.sell.dataObject.OrderMaster;
import com.xinpeng.sell.enums.OrderStatusEnum;
import com.xinpeng.sell.enums.PayStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author 吕新鹏
 * @Date 2018/6/18 22:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234");
        orderMaster.setBuyerName("新鹏");
        orderMaster.setBuyerAddress("青岛科技大学");
        orderMaster.setBuyerPhone("12345679");
        orderMaster.setBuyerOpenid("1234564");
        orderMaster.setOrderAmount(new BigDecimal(122.3));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = repository.save(orderMaster);
        assertNotNull(result);
    }


    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> result = repository.findByBuyerOpenid("123456", request);
        System.out.println(result.getTotalElements());
        assertNotEquals(0,result.getTotalElements());
    }

}