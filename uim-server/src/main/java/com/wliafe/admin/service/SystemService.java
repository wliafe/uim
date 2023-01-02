package com.wliafe.admin.service;

import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.security.details.BaseDetails;

public interface SystemService {
    boolean register(String email, String roleId);

    AjaxResult login(BaseDetails authenticate);
}
