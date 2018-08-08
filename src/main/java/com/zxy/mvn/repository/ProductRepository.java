package com.zxy.mvn.repository;

import com.zxy.mvn.dataobject.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductStatus(Integer productStatus);
}
