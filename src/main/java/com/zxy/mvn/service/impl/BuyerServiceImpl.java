package com.zxy.mvn.service.impl;

import com.zxy.mvn.dto.OrderDTO;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.service.BuyerService;
import com.zxy.mvn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public void cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到订单，orderId={}", orderId);
            throw new MvnException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderService.cancle(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单详情】订单的openid不一致，openid={}", openid);
            throw new MvnException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
