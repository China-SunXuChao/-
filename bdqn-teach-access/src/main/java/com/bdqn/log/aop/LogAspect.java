package com.bdqn.log.aop;
import com.bdqn.log.service.OperationLogService;
import com.teach.aop.OperationProperties;
import com.teach.entity.log.SysLog;
import com.teach.response.ProfileResult;
import com.teach.utils.HttpContextUtil;
import com.teach.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private OperationProperties operationProperties;

    @Autowired(required = false)
    private OperationLogService operationLogService;

    @Pointcut("@annotation(com.teach.aop.Log)")
    public void pointcut(){ }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result =null;
        long beginTime = System.currentTimeMillis();
        result = point.proceed();
        //获取request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        //设置ip地址
        String ip = IPUtil.getIpAddr(request);
        String username = "";
        if (operationProperties.isOpenAopLog()){ //保存日志
            ProfileResult profileResult = (ProfileResult) SecurityUtils.getSubject().getPrincipal();    //获取shiro的安全数据
            String nickName = profileResult.getNickName();
            username = nickName;
            //装载系统日志类
            SysLog sysLog = new SysLog();
            sysLog.setUsername(username);
            sysLog.setIp(ip);
            sysLog.setTime(beginTime);
            operationLogService.save(point,sysLog);
        }
        return result;
    }

}
