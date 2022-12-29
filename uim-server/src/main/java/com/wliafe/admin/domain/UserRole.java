package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "uim_user_role")
public class UserRole implements Serializable {
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "nick_name")
    private String nickName;
    @TableField(value = "role_id")
    private String roleId;
    @TableField(value = "role_name")
    private String roleName;
}
