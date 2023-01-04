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

    @GetMapping("/code/email")
    public AjaxResult getCodeByEmail(@RequestParam String email) {
        mailService.sendMailForActivationAccount(codeService.setCode(email), email);
        return AjaxResult.success();
    }

    @PostMapping("/register/email")
    public AjaxResult register(@RequestBody Register register) {
        if (codeService.codeNotRight(register.getEmail(), register.getCode())) throw new RuntimeException("验证失败");
        if (Objects.isNull(roleService.getById(register.getRoleId()))) throw new RuntimeException("角色不存在");
        User user = userService.selectByEmail(register.getEmail());
        if (Objects.isNull(user)) {
            user = new User();
            user.setEmail(register.getEmail());
        } else if (!Objects.isNull(userRoleService.selectByUserIdAndRoleId(user.getUserId(), register.getRoleId())))
            throw new RuntimeException("邮箱已注册");
        systemService.register(user, register.getRoleId());
        return AjaxResult.success("用户添加成功");
    }

    @PostMapping("/login/email/code")
    public AjaxResult login(@RequestBody Login login) {
        AuthenticationToken authentication = new AuthenticationToken(login.getEmail(), login.getCode());
        return systemService.login(authentication);
    }

    @RequestMapping("/logout")
    public AjaxResult logout() {
        return systemService.logout();
    }
}
