package com.wliafe.admin.controller;

import com.wliafe.admin.domain.User;
import com.wliafe.admin.service.RoleService;
import com.wliafe.admin.service.UserRoleService;
import com.wliafe.admin.service.UserService;
import com.wliafe.common.domain.Login;
import com.wliafe.common.domain.Register;
import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.admin.service.SystemService;
import com.wliafe.common.service.MailService;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.service.CodeService;
import com.wliafe.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 系统接口
 *
 * @author wliafe
 * @date 2023/1/3 15:16
 **/
@RestController
@RequestMapping("/sys")
public class SystemController {
    @Autowired
    private MailService mailService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Operation(summary = "通过邮箱获取验证码")
    @Parameter(name = "email", example = "wliafe@163.com", required = true, in = ParameterIn.QUERY)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "500", description = "请求失败")
    })
    @GetMapping("/code/email")
    public AjaxResult getCodeByEmail(@RequestParam String email) {
        mailService.sendMailForActivationAccount(codeService.setCode(email), email);
        return AjaxResult.success();
    }

    @Operation(summary = "通过邮箱注册", description = "所需属性 email code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "注册成功"),
            @ApiResponse(responseCode = "500", description = "注册失败")
    })
    @PostMapping("/register/email")
    public AjaxResult register(@RequestBody Register register) {
        if (codeService.codeNotRight(register.getEmail(), register.getCode())) throw new RuntimeException("注册验证失败");
        if (Objects.isNull(roleService.getById(register.getRoleId()))) throw new RuntimeException("注册角色不存在");
        User user = userService.getByEmail(register.getEmail());
        if (Objects.isNull(user)) {
            user = new User();
            user.setEmail(register.getEmail());
        } else if (!Objects.isNull(userRoleService.selectByUserIdAndRoleId(user.getUserId(), register.getRoleId())))
            throw new RuntimeException("邮箱已注册");
        systemService.register(user, register.getRoleId());
        return AjaxResult.success("用户注册成功");
    }

    @Operation(summary = "通过邮箱登录", description = "所需属性 email code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "500", description = "登录失败")
    })
    @PostMapping("/login/email/code")
    public AjaxResult login(@RequestBody Login login) {
        AuthenticationToken authentication = new AuthenticationToken(login.getEmail(), login.getCode());
        return systemService.login(authentication);
    }

    @Operation(summary = "根据token获取用户信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "请求成功"),
            @ApiResponse(responseCode = "500", description = "请求失败")
    })
    @GetMapping("/get/token")
    public AjaxResult getByToken() {
        return systemService.getByToken();
    }

    @Operation(summary = "退出登录")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PostMapping("/logout")
    public AjaxResult logout() {
        return systemService.logout();
    }
}
