package com.wliafe.common.service;

import com.wliafe.common.domain.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 超管认证服务
 *
 * @author wliafe
 * @date 2023/1/12 0:16
 **/
@Service
public class AdminService {
    @Value("${role.admin.phone}")
    private String adminPhone;
    @Value("${role.admin.email}")
    private String adminEmail;

    public boolean isAdmin(Verification verification) {
        if (isAdminPhone(verification.getPhone())) return true;
        else return isAdminEmail(verification.getEmail());
    }

    private boolean isAdminPhone(String phone) {
        if (Objects.isNull(phone)) return false;
        else return phone.equals(adminPhone);
    }

    private boolean isAdminEmail(String email) {
        if (Objects.isNull(email)) return false;
        else return email.equals(adminEmail);
    }
}
