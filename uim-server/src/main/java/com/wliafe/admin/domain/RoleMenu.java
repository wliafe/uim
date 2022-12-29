package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("uim_role_menu")
public class RoleMenu {
    @TableField(value = "role_id")
    private String roleId;
    @TableField(value = "role_name")
    private String roleName;
    @TableField(value = "menu_id")
    private String menuId;
    @TableField(value = "menu_name")
    private String menuName;
}
