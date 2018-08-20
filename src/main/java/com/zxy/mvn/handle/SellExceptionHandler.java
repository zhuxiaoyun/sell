package com.zxy.mvn.handle;

import com.zxy.mvn.config.ProjectUrlConfig;
import com.zxy.mvn.domain.Result;
import com.zxy.mvn.exception.GirlException;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.exception.SellerAuthorizeException;
import com.zxy.mvn.utils.ResultUtil;
import com.zxy.mvn.utils.ResultVOUtil;
import com.zxy.mvn.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        if (e instanceof MvnException) {
            MvnException mvnException = (MvnException) e;
            return ResultVOUtil.error(mvnException.getCode(), mvnException.getMessage());
        } else {
            log.error("【系统异常】:", e);
            return ResultVOUtil.error(-1, "未知错误");
        }
    }

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login_index"));
    }
}
