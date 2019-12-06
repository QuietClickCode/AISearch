package com.zjj.aisearch.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zjj.aisearch.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haozz
 * @date 2018/6/19 17:16
 * @description
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    @ExceptionHandler(value = JWTVerificationException.class)//指定拦截的异常
    @ResponseBody
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();//打印异常信息
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg("未登录");
        responseResult.setStatus(-1);
        log.error("------------" + responseResult.toString());
        return responseResult;
    }
}
