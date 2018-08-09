package com.zxy.mvn.exception;

import com.zxy.mvn.enums.ResultEnum;

public class MvnException extends RuntimeException {

    private Integer code;

    public MvnException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public MvnException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
