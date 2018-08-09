package com.zxy.mvn.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxy.mvn.dataobject.OrderDetail;
import com.zxy.mvn.dto.OrderDTO;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        try {
            List<OrderDetail> orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
            orderDTO.setOrderDetailList(orderDetailList);
        } catch (Exception e) {
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new MvnException(ResultEnum.PARAM_ERROR);
        }

        return orderDTO;
    }
}
