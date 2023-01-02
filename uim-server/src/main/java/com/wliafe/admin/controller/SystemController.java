package com.wliafe.admin.controller;

import com.wliafe.common.domain.Login;
import com.wliafe.common.domain.Register;
import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.admin.service.SystemService;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.security.details.EmailCodeDetails;
import com.wliafe.common.service.MailService;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.service.CodeService;
import com.wliafe.common.utils.EmailUtil;
import com.wliafe.common.utils.RoleUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
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
    private AuthenticationManager authenticationManager;
    @GetMapping("/code/email")
    public AjaxResult getCodeByEmail(@RequestParam String email) {
        try {
            if (!EmailUtil.checkEmail(email)) throw new RuntimeException("邮箱格式不正确");
            mailService.sendMailForActivationAccount(codeService.setCode(email), email);
            return AjaxResult.success();
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/register/email")
    public AjaxResult register(@RequestBody Register register) {
        try {
            if (!RoleUtils.checkLength(register.getRoleId())) throw new RuntimeException("角色id错误");
            if (Objects.isNull(register.getEmail())) throw new RuntimeException("邮箱不能为空");
            if (EmailUtil.checkEmail(register.getEmail())) throw new RuntimeException("邮箱格式不正确");
            if (!codeService.checkCode(register.getEmail(), register.getCode())) throw new RuntimeException("验证失败");
            systemService.register(register.getEmail(), register.getCode());
            log.info("用户添加成功");
            return AjaxResult.success("用户添加成功");
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/login/email/code")
    public AjaxResult login(@RequestBody Login login) {
        System.out.println(login);
        AuthenticationToken authentication = new AuthenticationToken(login.getEmail(), login.getCode());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) throw new RuntimeException("登录失败");
        EmailCodeDetails emailCodeDetails = (EmailCodeDetails) authenticate.getPrincipal();
        BaseDetails baseDetails = new BaseDetails(emailCodeDetails.getUser(), emailCodeDetails.getPermissions());
        return systemService.login(baseDetails);
    }
}
