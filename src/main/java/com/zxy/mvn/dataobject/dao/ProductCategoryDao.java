package com.zxy.mvn.dataobject.dao;

import com.zxy.mvn.dataobject.ProductCategory;
import com.zxy.mvn.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int insert(ProductCategory category) {
        return mapper.insertByObject(category);
    }
}
