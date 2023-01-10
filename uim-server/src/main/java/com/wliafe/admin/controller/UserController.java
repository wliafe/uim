package com.wliafe.admin.controller;

import com.wliafe.admin.service.UserService;
import com.wliafe.common.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "根据邮箱获取用户信息")
    @Parameter(name = "email", example = "wliafe@163.com", required = true, in = ParameterIn.QUERY)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "500", description = "请求失败")
    })
    @PreAuthorize("hasAuthority('user:get')")
    @GetMapping("/get/email")
    public AjaxResult getByEmail(@RequestParam String email) {
        return AjaxResult.success(userService.getByEmail(email));
    }

    @Operation(summary = "请求测试")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "500", description = "请求失败")
    })
    @PreAuthorize("hasAuthority('14')")
    @GetMapping("/get")
    public AjaxResult get() {
        return AjaxResult.success();
    }
}