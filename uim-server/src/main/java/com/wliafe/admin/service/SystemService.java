package com.wliafe.admin.service;

import com.wliafe.admin.domain.User;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.security.authentication.AuthenticationToken;

public interface SystemService {
    boolean register(User user, String roleId);

    AjaxResult login(AuthenticationToken authentication);

    AjaxResult getByToken();

    AjaxResult logout();
}
