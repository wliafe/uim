package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("uim_menu")
public class Menu implements Serializable {
    @TableId(value = "menu_id", type = IdType.ASSIGN_UUID)
    private String menuId;
    @TableField(value = "menu_name")
    private String menuName;
    private String path;
    private String component;
    private String perms;
    private String icon;
    private boolean visible;
    private boolean status;
    @TableField(value = "del_flag")
    @TableLogic(value = "0", delval = "1")
    private boolean delFlag;
    @TableField(value = "create_by")
    private String createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_by")
    private String updateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String remark;
}
