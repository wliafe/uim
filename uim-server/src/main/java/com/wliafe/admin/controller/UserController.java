package com.wliafe.admin.controller;

import com.alibaba.fastjson.JSON;
import com.wliafe.admin.service.UserService;
import com.wliafe.admin.domain.User;
import com.wliafe.common.exception.ControllerException;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.domain.ServiceResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody Map<String, Object> map) {
        try {
//            验证码
            String code = (String) map.get("code");
            if (!code.equals("1234")) throw new ControllerException("验证码填写错误");
//            禁止创建超管
            String roleId = (String) map.get("roleId");
            if (roleId.length() != 32) throw new ControllerException("身份id错误");
//            接收user
            User user = JSON.parseObject(JSON.toJSONString(map.get("user")), User.class);
//            TODO 邮箱、手机、密码验证
            if (user.getPassword() != null) {
                throw new ControllerException("暂无密码验证");
            } else if (user.getPhone() != null) {
                throw new ControllerException("暂无手机验证");
            } else if (user.getEmail() != null) {
                if (userService.selectByEmail(user.getEmail()).get(ServiceResult.DATA_TAG) != null)
                    throw new ControllerException("邮箱已注册");
            } else throw new ControllerException("缺少身份验证条件");
//            补全user
            String userId = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUserId(userId);
            String nickName = "用户_" + userId.substring(22);
            user.setNickName(nickName);
            Date loginDate = new Date();
            user.setLoginDate(loginDate);
//            添加用户
            userService.register(user, roleId);
            log.info("用户添加成功");
            return AjaxResult.success("用户添加成功");
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
    }

    @ApiOperation("根据邮箱获取用户信息")
    @GetMapping("/get/email")
    public AjaxResult getByEmail(@RequestParam String email) {
        return AjaxResult.success(userService.selectByEmail(email).get(ServiceResult.DATA_TAG));
    }
}