package com.wliafe.admin.controller;

import com.wliafe.common.config.mail.MailConfig;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.service.CodeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sys")
public class SystemController {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private CodeService codeService;

    @ApiOperation("获取验证码")
    @GetMapping("/code/email")
    public AjaxResult code(@RequestParam String email) {
        mailConfig.sendMailForActivationAccount(codeService.setCode(email), email);
        return AjaxResult.success();
    }
}
