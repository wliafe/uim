package com.wliafe.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "注册类型")
public class Register implements Serializable {
@Schema(description = "电话",example = "15265232757")
    private String phone;
    @Schema(description = "邮箱", example = "wliafe@163.com")
    private String email;
    @Schema(description = "角色id",example = "3893d5c6374d695ede830f2d2e3e4f2d")
    private String roleId;
    @Schema(description = "验证码", example = "1234")
    private String code;
}
