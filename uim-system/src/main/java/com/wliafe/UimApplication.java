package com.wliafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//Mybatis-plus
@MapperScan("com.wliafe.admin.mapper")
//数据库事务
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
//filter 过滤器
//@ServletComponentScan
public class UimApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimApplication.class, args);
    }
}
