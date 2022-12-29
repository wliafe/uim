package com.wliafe.admin.controller;

import com.wliafe.common.config.mail.MailConfig;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.utils.CodeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sys")
public class SystemController {
    @Autowired
    MailConfig mailConfig;

    @ApiOperation("获取验证码")
    @GetMapping("/email/code")
    public AjaxResult code(@RequestParam String email) {
        String code = CodeUtil.randomCode();
        mailConfig.sendMailForActivationAccount(code, email);
        return AjaxResult.success();
    }
}
