package com.wliafe.admin.mapper;

import com.wliafe.admin.domain.UserRole;
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
        int data = userRoleMapper.insert(new UserRole("123453", "458965"));
        System.out.println(data);
    }
}
