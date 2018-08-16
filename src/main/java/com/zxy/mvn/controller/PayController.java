package com.zxy.mvn.controller;

import com.zxy.mvn.dto.OrderDTO;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            log.error("【支付订单】订单不存在，orderId={}", orderId);
            throw new MvnException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付


    }
}
