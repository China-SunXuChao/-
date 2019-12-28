package com.teach.error;

import com.teach.response.Result;
import com.teach.response.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的公共异常处理器
 *  1.声明异常处理器
 *  2.对异常统一处理
 */
//@ControllerAdvice //@controlelr注解的增强
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, Exception e) {

        if(e.getClass() == CommonException.class) {
            //类型转型
            CommonException ce = (CommonException) e;
            return new Result(ce.getResultCode());
        }else{
            return new Result(ResultCode.FAIL);
        }
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return new Result(ResultCode.UNAUTHORISE);
    }





}
