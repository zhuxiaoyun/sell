package com.zxy.mvn.service;

import com.zxy.mvn.dto.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    void cancelOrder(String openid, String orderId);
}
