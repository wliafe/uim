package com.wliafe.admin.service;

public interface RoleService {
    boolean isExist(String roleId);

    boolean isNotExist(String roleId);
}
