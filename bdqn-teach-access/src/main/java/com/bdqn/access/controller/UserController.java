package com.bdqn.access.controller;

import com.bdqn.access.service.UserService;
import com.teach.entity.access.Teacher;
import com.teach.entity.access.User;
import com.teach.response.ProfileResult;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import com.teach.vo.UserTeacherVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;


/*    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Result logout() throws Exception {
        return userService.logout();
    }*/

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,Object> map){
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        try {
            //1.构造登陆令牌UsernamePasswordToken
            // 加密密码
            password = new Md5Hash(password,username,3).toString();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法,进入realm完成认证
            subject.login(usernamePasswordToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //构造返回结果
            return new Result(ResultCode.SUCCESS,sessionId);
        }catch (Exception e){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }


    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public Result profile(HttpServletRequest request){
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principalCollection = subject.getPrincipals();
        //2。获取安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        System.err.println(result);
        return new Result(ResultCode.SUCCESS,result);
    }


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestBody Map<String,Object> map){
        return userService.list(map);
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result save(@RequestBody UserTeacherVo vo){
        User user = new User();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(vo,user);
        BeanUtils.copyProperties(vo,teacher);
        return userService.save(user,teacher);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody UserTeacherVo vo){
        User user = new User();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(vo,user);
        BeanUtils.copyProperties(vo,teacher);
        return userService.update(user,teacher);
    }

}
