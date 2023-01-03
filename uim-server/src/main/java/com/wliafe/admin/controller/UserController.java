package com.wliafe.admin.controller;

import com.wliafe.admin.service.UserService;
import com.wliafe.common.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 用户接口
 *
 * @author wliafe
 * @date 2023/1/3 15:16
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('user:get')")
    @GetMapping("/get/email")
    public AjaxResult getByEmail(@RequestParam String email) {
        return AjaxResult.success(userService.selectByEmail(email));
    }

    @PreAuthorize("hasAuthority('14')")
    @GetMapping("/get")
    public AjaxResult get() {
        return AjaxResult.success();
    }
}