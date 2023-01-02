package com.wliafe.admin.controller;

import com.wliafe.admin.service.UserService;
import com.wliafe.common.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @auther wliafe
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/after-login/get/email")
    public AjaxResult getByEmail(@RequestParam String email) {
        return AjaxResult.success(userService.selectByEmail(email));
    }
}