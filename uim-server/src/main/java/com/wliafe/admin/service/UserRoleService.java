package com.wliafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wliafe.admin.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {
    public UserRole selectByUserIdAndRoleId(String userId, String roleId);
}
