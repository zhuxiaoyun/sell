package com.zxy.mvn.enums;

/**
 * 订单状态
 */
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISH(1, "已完结"),
    CANCEL(2, "取消订单"),
    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
