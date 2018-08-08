package com.zxy.mvn.service;

import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 */
public interface ProductService {

    Product findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<Product> findUpAll();

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
