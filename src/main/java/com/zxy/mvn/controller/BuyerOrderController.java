package com.zxy.mvn.controller;

import com.zxy.mvn.converter.OrderForm2OrderDTOConverter;
import com.zxy.mvn.dto.OrderDTO;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.form.OrderForm;
import com.zxy.mvn.service.BuyerService;
import com.zxy.mvn.service.OrderService;
import com.zxy.mvn.utils.ResultUtil;
import com.zxy.mvn.utils.ResultVOUtil;
import com.zxy.mvn.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new MvnException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new MvnException(ResultEnum.CART_EMPTY);
        }

        OrderDTO orderResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<OrderDTO> list(@RequestParam("openid") String openid,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【订单列表】微信openid不能为空");
            throw new MvnException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //查询订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO,不安全的做法，改进
        if (StringUtils.isEmpty(orderId)) {
            log.error("【订单详情】orderId不能为空");
            throw new MvnException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO,不安全的做法，改进
        if (StringUtils.isEmpty(orderId)) {
            log.error("【取消订单】orderId不能为空");
            throw new MvnException(ResultEnum.PARAM_ERROR);
        }

        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

    //支付订单

}
