package com.wliafe.admin.controller.system;

import com.wliafe.common.domain.Login;
import com.wliafe.common.domain.MyResult;
import com.wliafe.common.security.authentication.EmailCodeAuthenticationToken;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.service.CodeService;
import com.wliafe.common.service.MailService;
import com.wliafe.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author wliafe
 * @date 2023/1/24 11:32
 **/
@RestController
@RequestMapping("/v1/system")
public class SystemController {
    @Autowired
    private MailService mailService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "通过邮箱获取验证码")
    @Parameter(name = "email", example = "wliafe@qq.com", required = true, in = ParameterIn.QUERY)
    @ApiResponse(responseCode = "200", description = "请求成功")
    @GetMapping("/code/email")
    public MyResult getCodeByEmail(@RequestParam String email) {
        mailService.sendMailForActivationAccount(codeService.setCode(email), email);
        return MyResult.success();
    }

    @Operation(summary = "通过邮箱登录", description = "所需属性 email code")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PostMapping("/login/email/code")
    public MyResult loginByEmailCode(@RequestBody Login login) {
        if (Objects.isNull(login)) throw new RuntimeException("登录参数为空");
        EmailCodeAuthenticationToken authenticationToken = new EmailCodeAuthenticationToken(login.getEmail(), login.getCode());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return login(authentication);
    }

    @Operation(summary = "退出登录")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @DeleteMapping("/logout")
    public MyResult logout() {
        tokenService.logout();
        return MyResult.success("已退出登录");
    }

    private MyResult login(Authentication authentication) {
        if (Objects.isNull(authentication)) throw new RuntimeException("登录失败");
        BaseDetails baseDetails = (BaseDetails) authentication.getPrincipal();
        String token = tokenService.setToken(baseDetails);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return MyResult.success("登录成功", map);
    }
}
