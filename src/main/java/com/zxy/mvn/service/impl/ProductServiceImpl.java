package com.zxy.mvn.service.impl;

import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.dto.CartDTO;
import com.zxy.mvn.enums.ProductStatusEnum;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.repository.ProductRepository;
import com.zxy.mvn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findOne(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public List<Product> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productRepository.findOne(cartDTO.getProductId());
            if (product == null) {
                throw new MvnException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = product.getProductStock() + cartDTO.getProductNum();
            product.setProductStock(result);
            productRepository.save(product);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Product product = productRepository.findOne(cartDTO.getProductId());
            if (product == null) {
                throw new MvnException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = product.getProductStock() - cartDTO.getProductNum();
            if (result < 0) {
                throw new MvnException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            product.setProductStock(result);
            productRepository.save(product);
        }
    }
}
