package com.wliafe.admin.mapper;

import com.wliafe.admin.domain.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuMapperTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    void testInsert() {
        Menu menu = new Menu();
        menu.setPerms("user:gets");
        menuMapper.insert(menu);
    }
}
