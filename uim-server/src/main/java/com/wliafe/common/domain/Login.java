package com.wliafe.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录接收类型
 *
 * @author wliafe
 * @date 2023/1/12 0:06
 **/
@Data
@Schema(description = "登录参数")
public class Login implements Serializable {
    @Schema(description = "电话", example = "15265232757")
    private String phone;
    @Schema(description = "邮箱", example = "wliafe@qq.com")
    private String email;
    @Schema(description = "密码", example = "wliafe")
    private String password;
    @Schema(description = "验证码", example = "1234")
    private String code;
}
