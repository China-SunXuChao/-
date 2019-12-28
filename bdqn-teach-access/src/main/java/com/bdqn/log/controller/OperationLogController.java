package com.bdqn.log.controller;

import com.bdqn.log.service.OperationLogService;
import com.teach.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/getLogMonitoring",method = RequestMethod.POST)
    public Result getLogMonitoring(@RequestBody  Map<String,Object> map){
        return operationLogService.getLogMonitoring(map);
    }

}
