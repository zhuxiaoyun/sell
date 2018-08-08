package com.zxy.mvn.service;

import com.zxy.mvn.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    ProductCategory save(ProductCategory productCategory);
}
