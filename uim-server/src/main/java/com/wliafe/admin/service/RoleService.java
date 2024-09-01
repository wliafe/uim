package com.wliafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wliafe.admin.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wliafe
 * @date 2023/1/12 0:01
 **/
public interface RoleService extends IService<Role> {
    void save(Role role, List<String> menuIdList);

    void update(Role role, List<String> menuIdList);
}
