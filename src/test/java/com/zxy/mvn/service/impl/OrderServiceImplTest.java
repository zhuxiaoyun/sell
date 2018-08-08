package com.zxy.mvn.service.impl;

import com.zxy.mvn.dataobject.OrderDetail;
import com.zxy.mvn.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "15336417933531737595";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerName("宝宝呀");
        orderDTO.setBuyerAddress("西湖呀");
        orderDTO.setBuyerPhone("123456789012");

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123458");
        o1.setProductNum(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductNum(3);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        String orderId = ORDER_ID;
        OrderDTO result = orderService.findOne(orderId);
        log.info("【查询订单】result={}", result);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0, result.getOrderDetailList().size());
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        log.info("【查询订单列表】result={}", orderDTOPage);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void pay() {
    }

    @Test
    public void cancle() {
    }

    @Test
    public void finish() {
    }
}