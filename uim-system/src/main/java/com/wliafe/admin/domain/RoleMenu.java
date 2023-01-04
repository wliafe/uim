package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("uim_role_menu")
public class RoleMenu implements Serializable {
    @TableField(value = "role_id")
    private String roleId;
    @TableField(value = "menu_id")
    private String menuId;
}
