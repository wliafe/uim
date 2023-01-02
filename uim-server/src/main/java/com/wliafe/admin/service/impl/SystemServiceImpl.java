package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wliafe.admin.domain.Role;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.domain.UserRole;
import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.admin.mapper.UserRoleMapper;
import com.wliafe.admin.service.SystemService;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.service.TokenService;
import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleMapper roleMapper;
    @Autowired
    private TokenService tokenService;


    @Override
    @Transactional
    public boolean register(String email, String roleId) {
        Role role = roleMapper.selectById(roleId);
        if (Objects.isNull(role)) throw new RuntimeException("角色不存在");
        User user = selectByEmail(email);
        if (Objects.isNull(user)) {
            user = new User(UUIDUtil.getUUID(), "用户_" + UUIDUtil.getUUID(10));
            user.setEmail(email);
            userMapper.insert(user);
        } else if (!Objects.isNull(selectByUserIdAndRoleId(user.getUserId(), roleId)))
            throw new RuntimeException("邮箱已注册");
        userRoleMapper.insert(new UserRole(user.getUserId(), user.getNickName(), role.getRoleId(), role.getRoleName()));
        return true;
    }


    @Override
    public AjaxResult login(BaseDetails baseDetails) {
        String token = tokenService.setToken(baseDetails);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return AjaxResult.success("登陆成功", map);
    }

    public User selectByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }

    private UserRole selectByUserIdAndRoleId(String userId, String roleId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        queryWrapper.eq(UserRole::getRoleId, roleId);
        return userRoleMapper.selectOne(queryWrapper);
    }
}
