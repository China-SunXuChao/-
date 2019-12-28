package com.teach.aop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "operation")
public class OperationProperties {
    //是否开启异步记录用户操作日志
    private boolean openAopLog = true;
}
