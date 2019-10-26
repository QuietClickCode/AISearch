package com.zjj.aisearch.aop;

import com.zjj.aisearch.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建日期:2018年4月6日<br/>
 * 代码创建:黄聪<br/>
 * 功能描述:<br/>
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:直接处理 HttpMessageNotReadableException 报错的信息<br/>
     * @param ex
     * @return
     */

    /**
     * 全局异常,用来捕获未授权异常,shiro的noauth,主要是为了配合前端
     * @param ex
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public Object requestNotReadable(UnauthorizedException ex){

        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg("未授权");
        responseResult.setStatus(-1);
        responseResult.setUrl("/noauth");
        return responseResult;
    }

}
