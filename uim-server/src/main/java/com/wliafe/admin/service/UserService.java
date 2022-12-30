package com.wliafe.admin.service;

import com.wliafe.admin.domain.User;
import com.wliafe.common.domain.ServiceResult;

public interface UserService {
    ServiceResult register(User user, String roleId);

    ServiceResult selectByEmail(String email);

    boolean emailIsRegister(String email);

    boolean emailNotRegister(String email);
}
