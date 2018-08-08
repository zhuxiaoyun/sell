package com.zxy.mvn.service.impl;

import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired ProductServiceImpl productService;

    @Test
    public void findOne() {
        Product result = productService.findOne("123456");
        Assert.assertNotNull(result);
        Assert.assertEquals("123456", result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<Product> result = productService.findUpAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<Product> result = productService.findAll(pageRequest);
//        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void save() {
        Product product = new Product();
        product.setProductId("123457");
        product.setProductName("皮皮虾");
        product.setProductPrice(new BigDecimal(3.2));
        product.setProductStock(100);
        product.setProductDescription("好吃的吓");
        product.setProductIcon("www.dsdsd.jpg");
        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        product.setCategoryType(1);

        Product result = productService.save(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryType() {
    }
}