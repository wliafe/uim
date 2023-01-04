package com.wliafe.admin.service.impl;

import com.wliafe.admin.domain.User;
import com.wliafe.admin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

//        @Test
//    void testRegister() {
//        User user = new User();
//        userService.register(user,"admin" );
//    }
    @Test
    void testSelectByEmail() {
        User user = userService.selectByEmail("wliafe");
        System.out.println(user);
    }

}
