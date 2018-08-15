package com.zxy.mvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 买家端订单
 */

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    /**
     * 订单列表
     * @param page 第几页，从1开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size) {
        return null;
    }
}
