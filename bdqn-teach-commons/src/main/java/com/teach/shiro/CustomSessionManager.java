package com.teach.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * sessionID管理
 * 获取sessionid  token
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 头信息中具有sessionid
     *  请求头：Authorization： sessionid
     *
     *  指定sessionId的获取方式
     */

    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        //获取请求头Authorization中的数据
        String id = WebUtils.toHttp(request).getHeader("Autorization");
        if (StringUtils.isEmpty(id)){
            //如果用户已经登陆并把sessionID保存到redis中时,如果访问其他接口没有携带token，生成新的sessionID
            return super.getSessionId(request,response);
        }else {
            //请求头信息:Bearer sessionid
            id = id.replaceAll("Bearer ","");
            //返回sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }


}
