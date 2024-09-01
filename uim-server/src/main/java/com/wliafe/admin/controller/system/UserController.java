package com.wliafe.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wliafe.admin.domain.User;
import com.wliafe.admin.service.UserService;
import com.wliafe.common.domain.MyResult;
import com.wliafe.common.domain.UserParameters;
import com.wliafe.common.service.CodeService;
import com.wliafe.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 用户接口
 *
 * @author wliafe
 * @date 2023/1/3 15:16
 **/
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private CodeService codeService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Operation(summary = "通过邮箱注册", description = "所需属性 email code roleIdList")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PostMapping("/register/email")
    public MyResult registerByEmail(@RequestBody UserParameters userParameters) {
        //参数验证
        if (Objects.isNull(userParameters)) throw new RuntimeException("注册参数为空");
        //参数提取
        User user = userParameters.getUser();
        //验证码验证
        if (codeService.codeNotRight(user.getEmail(), userParameters.getCode()))
            throw new RuntimeException("注册验证失败");
        //注册
        if (Objects.isNull(userService.getByEmail(user.getEmail()))) {
            userService.register(user, userParameters.getRoleIdList());
        } else throw new RuntimeException("邮箱已注册");
        return MyResult.success("用户注册成功");
    }

    @Operation(summary = "更新个人用户信息")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:update')")
    @PutMapping("/update")
    public MyResult updateUser(@RequestBody UserParameters userParameters) {
        if (Objects.isNull(userParameters)) throw new RuntimeException("用户参数为空");
        //获取参数
        User user = userParameters.getUser();
        //设置个人id
        user.setUserId(tokenService.getUserId());
        //设置更新人
        user.setUpdateBy(tokenService.getUserId());
        //更新
        userService.update(user, userParameters.getRoleIdList());
        return MyResult.success();
    }

    @Operation(summary = "更新用户信息")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:updates')")
    @PutMapping("/updates")
    public MyResult updateUsers(@RequestBody UserParameters userParameters) {
        if (Objects.isNull(userParameters)) throw new RuntimeException("用户参数为空");
        //获取参数
        User user = userParameters.getUser();
        //设置更新人
        user.setUpdateBy(tokenService.getUserId());
        //更新
        userService.update(user, userParameters.getRoleIdList());
        return MyResult.success();
    }

    @Operation(summary = "获取个人用户信息")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:get')")
    @GetMapping("/get")
    public MyResult getUser() {
        String userId = tokenService.getUserId();
        return MyResult.success(userService.getById(userId));
    }

    @Operation(summary = "获取用户信息")
    @Parameters({
            @Parameter(name = "page", example = "1", required = true, in = ParameterIn.PATH),
            @Parameter(name = "size", example = "10", required = true, in = ParameterIn.PATH)
    })
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:gets')")
    @GetMapping("/gets/{page}/{size}")
    public MyResult getUsers(@PathVariable Integer page, @PathVariable Integer size) {
        Page<User> users = userService.page(new Page<>(page, size));
        return MyResult.success(users);
    }

    @Operation(summary = "注销个人用户")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:delete')")
    @DeleteMapping("/delete")
    public MyResult deleteUser() {
        String userId = tokenService.getUserId();
        userService.removeById(userId);
        return MyResult.success();
    }

    @Operation(summary = "注销用户")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('user:deletes')")
    @DeleteMapping("/deletes")
    public MyResult deleteUsers(@RequestParam String userId) {
        if (Objects.isNull(userId)) throw new RuntimeException("用户参数为空");
        userService.removeById(userId);
        return MyResult.success();
    }
}
