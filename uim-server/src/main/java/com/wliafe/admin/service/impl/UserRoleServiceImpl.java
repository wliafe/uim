package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wliafe.admin.domain.UserRole;
import com.wliafe.admin.mapper.UserRoleMapper;
import com.wliafe.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
