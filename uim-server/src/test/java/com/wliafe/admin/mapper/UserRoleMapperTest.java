package com.wliafe.admin.mapper;

import com.wliafe.admin.mapper.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRoleMapperTest {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    void testInsertData() {
        int data = userRoleMapper.insertData("123453", "11111", "458965", "5544");
        System.out.println(data);
    }
}
