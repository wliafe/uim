package com.wliafe.admin.service.impl;

import com.wliafe.admin.domain.User;
import com.wliafe.admin.domain.UserRole;
import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.admin.mapper.UserRoleMapper;
import com.wliafe.admin.service.SystemService;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.service.TokenService;
import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Objects;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public boolean register(User user, String roleId) {
        if (Objects.isNull(user.getUserId())) {
            user.setUserId(UUIDUtil.getUUID());
            user.setNickName("用户_" + UUIDUtil.getUUID(10));
            userMapper.insert(user);
        }
        userRoleMapper.insert(new UserRole(user.getUserId(), roleId));
        return true;
    }


    @Override
    public AjaxResult login(AuthenticationToken authentication) {
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) throw new RuntimeException("登录失败");
        BaseDetails baseDetails = (BaseDetails) authenticate.getPrincipal();
        String token = tokenService.setToken(baseDetails);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return AjaxResult.success("登陆成功", map);
    }
}
