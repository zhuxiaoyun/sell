package com.zxy.mvn.form;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目编号 */
    private Integer categoryType;

    /** 类目名称 */
    private  String categoryName;
}
