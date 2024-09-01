package com.wliafe.common.domain;

import com.wliafe.admin.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 注册接收类型
 *
 * @author wliafe
 * @date 2023/1/12 0:06
 **/
@Data
@Schema(description = "注册参数")
public class UserParameters implements Serializable {
    @Schema(description = "用户实体类")
    private User user;
    @Schema(description = "用户角色id数组")
    private List<String> roleIdList;
    @Schema(description = "验证码", example = "1234")
    private String code;
}
