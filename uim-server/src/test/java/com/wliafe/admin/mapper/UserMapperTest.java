package com.wliafe.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    void testSelectByEmail() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, "wliafe@163.com");
        User employee = userMapper.selectOne(queryWrapper);
        System.out.println(employee);
    }

    @Test
    void testInsert() {
        User user = new User();
        userMapper.insert(user);
    }

    @Test
    void testDelete() {
        userMapper.deleteById("afa85e6761cfa9066b68403088d737e4");
    }

    @Test
    void testUpdate() {
        User user = new User();
        userMapper.updateById(user);
    }
}
