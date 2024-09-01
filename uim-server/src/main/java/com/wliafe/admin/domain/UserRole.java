package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户角色类
 *
 * @author wliafe
 * @date 2023/1/11 23:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "uim_user_role")
public class UserRole implements Serializable {
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "role_id")
    private String roleId;
}
