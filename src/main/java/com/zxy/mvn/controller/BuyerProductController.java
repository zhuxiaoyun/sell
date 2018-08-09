package com.zxy.mvn.controller;

import com.zxy.mvn.dataobject.Product;
import com.zxy.mvn.dataobject.ProductCategory;
import com.zxy.mvn.service.CategoryService;
import com.zxy.mvn.service.ProductService;
import com.zxy.mvn.utils.ResultVOUtil;
import com.zxy.mvn.vo.ProductListVO;
import com.zxy.mvn.vo.ProductVO;
import com.zxy.mvn.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1. 查询所有商家商品
        List<Product> productList = productService.findUpAll();

        //2. 查询类目（一次性查询）

        //传统循环方法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (Product product : productList) {
//            categoryTypeList.add(product.getCategoryType());
//        }
        //精简方法(java8, lambda)
        List<Integer> categoryTypeList = productList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductListVO> productListVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductListVO productListVO = new ProductListVO();
            productListVO.setCategoryType(productCategory.getCategoryType());
            productListVO.setCategoryName(productCategory.getCategoryName());

            List<ProductVO> productVOList = new ArrayList<>();
            for (Product product : productList) {
                if (productCategory.getCategoryType().equals(product.getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product, productVO);
                    productVOList.add(productVO);
                }
            }
            productListVO.setProductVOList(productVOList);
            productListVOList.add(productListVO);
        }

        return ResultVOUtil.success(productListVOList);
    }
}
