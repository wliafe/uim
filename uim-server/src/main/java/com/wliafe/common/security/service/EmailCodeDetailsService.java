package com.wliafe.common.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.common.security.details.EmailCodeDetails;
import com.wliafe.common.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmailCodeDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodeService codeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户邮箱或密码输入错误");
        }
        String code = codeService.getCode(username);
        //TODO 获取权限信息存入
        List<String> permissions = null;
        return new EmailCodeDetails(user, code, permissions);
    }
}
