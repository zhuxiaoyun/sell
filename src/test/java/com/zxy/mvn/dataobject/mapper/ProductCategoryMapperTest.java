package com.zxy.mvn.dataobject.mapper;

import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryType", 900);
        map.put("categoryName", "下着大雨");
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("下着大雨");
        category.setCategoryType(901);
        int result = mapper.insertByObject(category);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory category = mapper.findByCategoryType(900);
        Assert.assertEquals(Integer.valueOf("900"), category.getCategoryType());
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> categoryList = mapper.findByCategoryName("下着大雨");
        Assert.assertNotEquals(0, categoryList.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType(901, "呼呼呼");
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("下着大雨");
        category.setCategoryType(901);
        int result = mapper.updateByObject(category);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(901);
        Assert.assertEquals(1, result);
    }
}