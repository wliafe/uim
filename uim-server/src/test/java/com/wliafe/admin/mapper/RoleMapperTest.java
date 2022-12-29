package com.wliafe.admin.mapper;

import com.wliafe.admin.domain.Role;
import com.wliafe.admin.mapper.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void testInsert() {
        Role role = new Role();
//        role.setRoleName("超级管理员");
        role.setRoleName("管理员");
        roleMapper.insert(role);
    }

    @Test
    void testSelect() {
        System.out.println("select:"+roleMapper.selectById("1111"));
    }
}
