package com.zxy.mvn.service;

import com.zxy.mvn.dto.OrderDTO;

/**
 * 消息推送
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
