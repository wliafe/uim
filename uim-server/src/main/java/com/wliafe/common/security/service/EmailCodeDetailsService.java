package com.wliafe.common.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.mapper.MenuMapper;
import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.common.domain.Verification;
import com.wliafe.common.security.details.EmailCodeDetails;
import com.wliafe.common.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wliafe
 * @date 2023/1/12 0:15
 **/
@Service
public class EmailCodeDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private CodeService codeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) throw new AuthenticationServiceException("用户邮箱输入错误");
        Verification verification = new Verification(user.getUserId(), user.getPhone(), user.getEmail());
        List<String> permissions = menuMapper.getAuthoritiesByUserId(verification.getUserId());
        if (Objects.isNull(permissions)) permissions = new ArrayList<>();
        return new EmailCodeDetails(verification, username, codeService, permissions);
    }
}
