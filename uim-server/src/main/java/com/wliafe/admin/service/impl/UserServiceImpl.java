package com.wliafe.admin.service.impl;

import com.wliafe.admin.mapper.UserMapper;
import com.wliafe.admin.mapper.UserRoleMapper;
import com.wliafe.admin.service.UserService;
import com.wliafe.admin.domain.Role;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.common.exception.ServiceException;
import com.wliafe.common.domain.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 用户注册
     *
     * @param user   用户信息
     * @param roleId 角色id
     * @return 结果
     * @auther wliafe
     */
    @Override
    @Transactional
    public ServiceResult register(User user, String roleId) {
//            禁止创建超管
        if (roleId.equals("admin")) throw new ServiceException("禁止创建超管");
//            验证role存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) throw new ServiceException("角色不存在");
//            插入user
        userMapper.insert(user);
        userRoleMapper.insertData(user.getUserId(), user.getNickName(), role.getRoleId(), role.getRoleName());
        return ServiceResult.success();
    }

    @Override
    public ServiceResult selectByEmail(String email) {
        return ServiceResult.success(userMapper.selectByEmail(email));
    }
}
