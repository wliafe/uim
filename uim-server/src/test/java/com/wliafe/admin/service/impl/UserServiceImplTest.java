package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void testSelectByEmail() {
        User user = userService.getByEmail("wliafe@163.com");
        System.out.println(user);
    }

    @Test
    void testGetUser() {
        Page<User> userPage = new Page<>(0, 10, 0);
        userService.page(userPage);
        System.out.println(userPage.getRecords());
    }
}
