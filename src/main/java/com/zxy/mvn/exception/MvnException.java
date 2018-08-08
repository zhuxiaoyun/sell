package com.zxy.mvn.exception;

import com.zxy.mvn.enums.ResultEnum;
import lombok.Data;

@Data
public class MvnException extends RuntimeException {

    private Integer code;

    public MvnException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.setCode(resultEnum.getCode());
    }
}
