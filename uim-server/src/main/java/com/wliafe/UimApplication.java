package com.wliafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wliafe.admin.mapper")
@EnableTransactionManagement
public class UimApplication {
    public static void main(String[] args) {
        SpringApplication.run(UimApplication.class, args);
    }
}
