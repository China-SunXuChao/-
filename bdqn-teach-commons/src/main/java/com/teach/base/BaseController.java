package com.teach.base;

import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String nickName;


    @ModelAttribute //在controller方法执行之前执行的方法
    public void setResAndReq(HttpServletResponse response,HttpServletRequest request){
        this.request = request;
        this.response = response;
    }
}
