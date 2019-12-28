package com.bdqn.log.service;

import com.bdqn.log.mapper.OperationLogMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teach.aop.Log;
import com.teach.entity.log.SysLog;
import com.teach.response.PageResult;
import com.teach.response.Result;
import com.teach.response.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;


@Service
@Transactional
public class OperationLogService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired(required = false)
    private OperationLogMapper operationLogMapper;


    public void save(ProceedingJoinPoint point, SysLog sysLog) throws JsonProcessingException {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);

        if (logAnnotation != null){
            sysLog.setOperation(logAnnotation.value());
        }

        String className = point.getClass().getName(); //获取请求的类名
        String methodName = signature.getName();   //请求的方法名
        sysLog.setMethod(className+"."+methodName+"()");

        Object[] args = point.getArgs();  //请求的方法参数值

        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();////请求的方法参数名称

        String[] parameterNames = u.getParameterNames(method);

        if (args != null && parameterNames!=null){
            StringBuilder params = new StringBuilder();
            params = this.handleParams(params,args, Arrays.asList(parameterNames));
            sysLog.setParams(params.toString());
        }

        sysLog.setCreateTime(new Date());

        Long beginTime = sysLog.getTime();
        Long time = System.currentTimeMillis() - beginTime;
        sysLog.setTime(time);
        operationLogMapper.insert(sysLog);
    }

    /**
     * 获得参数
     * @param params
     * @param args
     * @param paramNames
     * @return
     * @throws JsonProcessingException
     */
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List<Object> list = new ArrayList<>();
                List<Object> paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }

    public Result getLogMonitoring(Map<String, Object> map) {
        long total = operationLogMapper.getTotal(map);
        Integer page = Integer.parseInt(map.get("page").toString());
        Integer size = Integer.parseInt(map.get("size").toString());
        List<SysLog> list = operationLogMapper.getLogMonitoring((page-1) * size,size,map);
        PageResult<SysLog> sysLogPageResult = new PageResult<>(total,list);
        return new Result(ResultCode.SUCCESS,sysLogPageResult);
    }
}
