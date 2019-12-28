package com.teach.base;


import com.teach.response.Result;
import com.teach.response.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin  //跨域注解 允许任何请求，任何请求头，任何请求方法来请求数据
public class ErrorController {
    @RequestMapping("/authError")  //只有未登陆或权限不足 才会触发这个接口 这个接口不是用户主动触发的，而是通过shiro的过滤器自动触发
    public Result authError(int code){
        return code == 1 ? new Result(ResultCode.UNAUTHENTICATED) : new Result(ResultCode.UNAUTHORISE);
    }


}
