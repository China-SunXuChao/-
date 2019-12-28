package com;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.teach.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication(scanBasePackages = {"com.bdqn","com.teach"})
@EntityScan("com.teach")
public class MyAccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAccessApplication.class,args);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean //处理 no session
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }
}
