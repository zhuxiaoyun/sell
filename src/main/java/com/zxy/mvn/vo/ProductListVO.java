package com.zxy.mvn.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 商品列表（包含类目）
 */
public class ProductListVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductVO> productVOList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductVO> getProductVOList() {
        return productVOList;
    }

    public void setProductVOList(List<ProductVO> productVOList) {
        this.productVOList = productVOList;
    }
}
