package com.zxy.mvn.enums;

/**
 * 支付状态
 */
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
