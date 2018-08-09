package com.zxy.mvn.repository;

import com.zxy.mvn.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("123456");
        orderDetail.setDetailId("123456");
        orderDetail.setProductId("1");
        orderDetail.setProductName("粥");
        orderDetail.setProductIcon("xxx.png");
        orderDetail.setProductQuantity(2);
        orderDetail.setProductPrice(new BigDecimal(2.3));

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("123456");

        Assert.assertNotEquals(0,result.size());
    }
}