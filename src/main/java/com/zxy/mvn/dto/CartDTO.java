package com.zxy.mvn.dto;

import lombok.Data;

@Data
public class CartDTO {

    private String productId;

    private Integer productNum;

    public CartDTO(String productId, Integer productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }
}
