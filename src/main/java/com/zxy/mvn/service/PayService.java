package com.zxy.mvn.service;

import com.zxy.mvn.dto.OrderDTO;

/**
 * 支付
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
