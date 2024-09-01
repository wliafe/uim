package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色类
 *
 * @author wliafe
 * @date 2023/1/11 23:59
 **/
@Data
@TableName("uim_role")
@Schema(description = "角色实体类")
public class Role implements Serializable {
    @Schema(description = "id")
    @TableId(value = "role_id", type = IdType.NONE)
    private String roleId;
    @Schema(description = "名称")
    @TableField(value = "role_name")
    private String roleName;
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
