package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wliafe.admin.domain.Role;
import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.admin.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
