package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wliafe.admin.domain.RoleMenu;
import com.wliafe.admin.domain.UserRole;
import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.admin.mapper.UserRoleMapper;
import com.wliafe.admin.service.UserService;
import com.wliafe.admin.domain.User;
import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author wliafe
 * @date 2023/1/12 0:02
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public void register(User user, List<String> roleIdList) {
        //用户信息验证及插入
        if (Objects.isNull(user)) throw new RuntimeException("用户信息为空");
        user.setUserId(UUIDUtil.getUUID());
        user.setNickName("用户_" + UUIDUtil.getUUID(10));
        userMapper.insert(user);
        //用户角色信息验证及插入
        if (Objects.isNull(roleIdList)) return;
        for (String roleId : roleIdList)
            if (Objects.isNull(roleMapper.selectById(roleId))) throw new RuntimeException("角色不存在");
            else userRoleMapper.insert(new UserRole(user.getUserId(), roleId));
    }

    @Override
    public void update(User user, List<String> roleIdList) {
        //用户信息更新
        if (Objects.isNull(user)) throw new RuntimeException("用户信息为空");
        userMapper.updateById(user);
        //用户角色信息更新
        if (Objects.isNull(roleIdList)) return;
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, user.getUserId());
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        for (UserRole userRole : userRoles)
            if (roleIdList.contains(userRole.getRoleId())) roleIdList.remove(userRole.getRoleId());
            else userRoleMapper.deleteById(new RoleMenu(user.getUserId(), userRole.getRoleId()));
        for (String roleId : roleIdList)
            if (Objects.isNull(roleMapper.selectById(roleId))) throw new RuntimeException("角色不存在");
            else userRoleMapper.insert(new UserRole(user.getUserId(), roleId));
    }

    @Override
    public User getByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }
}
