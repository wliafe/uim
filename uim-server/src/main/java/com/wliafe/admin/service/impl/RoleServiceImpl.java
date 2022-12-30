package com.wliafe.admin.service.impl;

import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 角色存在
     *
     * @param roleId 角色id
     * @return 存在 true 不存在 false
     */
    @Override
    public boolean isExist(String roleId) {
        return !isNotExist(roleId);
    }

    /**
     * 角色不存在
     *
     * @param roleId 角色id
     * @return 存在 false 不存在 true
     */
    @Override
    public boolean isNotExist(String roleId) {
        return roleMapper.selectById(roleId) == null;
    }
}
