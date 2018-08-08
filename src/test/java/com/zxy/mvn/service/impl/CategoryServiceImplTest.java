package com.zxy.mvn.service.impl;

import com.zxy.mvn.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception{
        ProductCategory result = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), result.getCategoryId());
    }

    @Test
    public void findAll() throws Exception{
        List<ProductCategory> result = categoryService.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<Integer> categoryList = Arrays.asList(1,2,3);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(categoryList);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() throws Exception{
        ProductCategory productCategory = new ProductCategory(4, "男生");
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}