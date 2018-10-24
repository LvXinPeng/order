package com.xinpeng.sell.converter;

import com.xinpeng.sell.dataObject.OrderMaster;
import com.xinpeng.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吕新鹏
 * @Date 2018/6/19 21:24
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDto convert(OrderMaster orderMaster) {

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
