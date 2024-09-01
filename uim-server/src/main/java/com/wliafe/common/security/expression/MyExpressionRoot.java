package com.wliafe.common.security.expression;

import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ss")
public class MyExpressionRoot {
    @Autowired
    private AdminService adminService;

    public boolean hasAuthority(String authority) {
        AuthenticationToken authentication = (AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        BaseDetails baseDetails = (BaseDetails) authentication.getPrincipal();
        if (adminService.isAdmin(baseDetails.getVerification())) return true;
        List<String> permissions = baseDetails.getPermissions();
        return permissions.contains(authority);
    }
}
