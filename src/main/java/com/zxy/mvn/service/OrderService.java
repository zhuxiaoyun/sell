package com.zxy.mvn.service;

import com.zxy.mvn.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单
 */
public interface OrderService {

    //1. 创建订单
    OrderDTO create(OrderDTO orderDTO);

    //2. 查询单个订单
    OrderDTO findOne(String orderId);

    //3. 查询订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //4. 支付订单
    OrderDTO pay(OrderDTO orderDTO);

    //5. 取消订单
    OrderDTO cancle(OrderDTO orderDTO);

    //6. 完结订单
    OrderDTO finish(OrderDTO orderDTO);
}
