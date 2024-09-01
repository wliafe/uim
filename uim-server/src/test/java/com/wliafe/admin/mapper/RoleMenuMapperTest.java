package com.wliafe.admin.mapper;

import com.wliafe.admin.domain.RoleMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleMenuMapperTest {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Test
    void testInsert() {
        RoleMenu roleMenu = new RoleMenu("3893d5c6374d695ede830f2d2e3e4f2d","4714a4ba2761aeb52273a83c9d261611");
        roleMenuMapper.insert(roleMenu);
    }
}
