package com.wliafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UimApplicationTests {

    @Value("${role.admin.email}")
    private String adminEmail;

    @Test
    void contextLoads() {
        System.out.println(adminEmail);
    }

}
