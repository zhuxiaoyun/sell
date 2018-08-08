package com.zxy.mvn.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目编号 */
    private Integer categoryType;

    /** 类目名称 */
    private  String categoryName;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryType=" + categoryType +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public ProductCategory(Integer categoryType, String categoryName) {
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }

    public ProductCategory() {
    }
}
