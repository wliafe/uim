package com.wliafe.admin.mapper;

import com.wliafe.admin.domain.User;
import com.wliafe.admin.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> userMapperList = userMapper.selectList(null);
        userMapperList.forEach(System.out::println);
    }

    @Test
    void testSelectById() {
        User employee = userMapper.selectById(1);
        System.out.println(employee);
    }

    @Test
    void testInsert() {
        User user = new User("444","abc");
        userMapper.insert(user);
    }

    @Test
    void testDelete() {
        userMapper.deleteById("afa85e6761cfa9066b68403088d737e4");
    }

    @Test
    void testUpdate() {
        User user = new User("4eeba69cd824e95bd8c5ceca13d7da71", "ce");
        userMapper.updateById(user);
    }
}
