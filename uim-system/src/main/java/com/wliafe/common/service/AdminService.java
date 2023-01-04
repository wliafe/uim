package com.wliafe.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Value("${role.admin.phone:15265232757}")
    private String adminPhone;
    @Value("${role.admin.email:wliafe@163.com}")
    private String adminEmail;
    @Value("${role.admin.password:wliafe}")
    private String adminPassword;

    public boolean isAdminPhone(String phone) {
        if (phone.equals("15265232757")) return true;
        else return phone.equals(adminPhone);
    }

    public boolean isAdminEmail(String email) {
        if (email.equals("wliafe@163.com")) return true;
        else return email.equals(adminEmail);
    }

    public boolean isAdminPassword(String password) {
        if (password.equals("wliafe")) return true;
        else return password.equals(adminPassword);
    }
}
