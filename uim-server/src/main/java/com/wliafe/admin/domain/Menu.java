package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限类
 *
 * @author wliafe
 * @date 2023/1/11 23:58
 **/
@Data
@TableName("uim_menu")
@Schema(description = "权限实体类")
public class Menu implements Serializable {
    @Schema(description = "id")
    @TableId(value = "menu_id", type = IdType.ASSIGN_UUID)
    private String menuId;
    @Schema(description = "名称")
    @TableField(value = "menu_name")
    private String menuName;
    @Schema(description = "路径")
    private String path;
    @Schema(description = "组件")
    private String component;
    @Schema(description = "字段")
    private String perms;
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "是否可见")
    private boolean visible;
    @Schema(description = "状态")
    private boolean status;
    @Schema(description = "删除标志")
    @TableField(value = "del_flag")
    @TableLogic(value = "0", delval = "1")
    private boolean delFlag;
    @Schema(description = "创建者")
    @TableField(value = "create_by")
    private String createBy;
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @Schema(description = "更新者")
    @TableField(value = "update_by")
    private String updateBy;
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Schema(description = "备注")
    private String remark;
}
