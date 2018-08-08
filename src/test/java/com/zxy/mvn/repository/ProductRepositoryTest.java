package com.zxy.mvn.repository;

import com.zxy.mvn.dataobject.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveTest() {
        Product product = new Product();
        product.setProductId("123456");
        product.setProductName("皮达州");
        product.setProductPrice(new BigDecimal(3.2));
        product.setProductStock(100);
        product.setProductDescription("很好诶");
        product.setProductIcon("www.dsdsd.jpg");
        product.setProductStatus(0);
        product.setCategoryType(1);

        Product result = productRepository.save(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatusTest() throws Exception {
        List<Product> result = productRepository.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }

}