package com.wliafe.admin.controller;

import com.wliafe.admin.service.UserService;
import com.wliafe.admin.domain.User;
import com.wliafe.common.exception.ControllerException;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.domain.ServiceResult;
import com.wliafe.common.service.CodeService;
import com.wliafe.common.service.TokenService;
import com.wliafe.common.utils.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @auther wliafe
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private TokenService tokenService;

    /**
     * 用户注册
     *
     * @param map code(验证码) roleId(身份id) phone(电话) email(邮箱)
     * @return 结果
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody Map<String, Object> map) {
        try {
            String roleId = (String) map.get("roleId");
            if (RoleUtils.isLengthWrong(roleId)) throw new ControllerException("身份id错误");
            User user = new User();
            if (StringUtils.isNotNull(map.get("phone"))) {
                throw new ControllerException("暂无手机验证");
            } else if (StringUtils.isNotNull(map.get("email"))) {
                String email = (String) map.get("email");
                String code = (String) map.get("code");
                if (!codeService.checkCode(email, code)) throw new ControllerException("验证失败");
                if (userService.emailIsRegister(email))
                    throw new ControllerException("邮箱已注册");
                user.setEmail(email);
            } else throw new ControllerException("缺少身份验证条件");
//            补全user
            user.setUserId(UUIDUtil.getUUID());
            user.setNickName("用户_" + UUIDUtil.getUUID(10));
            user.setLoginDate(new Date());
//            添加用户
            userService.register(user, roleId);
            log.info("用户添加成功");
            return AjaxResult.success("用户添加成功");
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 登录
     *
     * @param map code(验证码) phone(手机) password(密码) email(邮箱)
     * @return 结果
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Map<String, Object> map) {
        if (StringUtils.isNotNull(map.get("password"))) {
            throw new ControllerException("暂无密码验证");
        } else if (StringUtils.isNotNull(map.get("phone"))) {
            throw new ControllerException("暂无手机验证");
        } else if (StringUtils.isNotNull(map.get("email"))) {
            String email = (String) map.get("email");
            String code = (String) map.get("code");
            if (!codeService.checkCode(email, code)) throw new ControllerException("验证失败");
            User user = (User) userService.selectByEmail(email).get(ServiceResult.DATA_TAG);
            if (StringUtils.isNull(user)) throw new ControllerException("邮箱未注册");
            return AjaxResult.success("用户登录成功", tokenService.setToken(user));
        } else throw new ControllerException("缺少身份验证条件");
    }

    @ApiOperation("根据邮箱获取用户信息")
    @GetMapping("/after-login/get/email")
    public AjaxResult getByEmail(@RequestParam String email) {
        return AjaxResult.success(userService.selectByEmail(email).get(ServiceResult.DATA_TAG));
    }
}