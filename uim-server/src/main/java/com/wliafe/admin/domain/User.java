package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 *
 * @author wliafe
 * @date 2023/1/11 23:59
 **/
@Data
@NoArgsConstructor
@TableName("uim_user")
@Schema(description = "用户实体类")
public class User implements Serializable {
    @Schema(description = "id")
    @TableId(value = "user_id", type = IdType.NONE)
    private String userId;
    @Schema(description = "名称")
    @TableField(value = "nick_name")
    private String nickName;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "性别")
    private Integer sex;
    @Schema(description = "生日")
    private Date birth;
    @Schema(description = "身份证号")
    @TableField(value = "id_number")
    private String idNumber;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "省")
    private String province;
    @Schema(description = "市")
    private String city;
    @Schema(description = "区")
    private String district;
    @Schema(description = "详细地址")
    private String address;
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
    @Schema(description = "登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "login_date")
    private Date loginDate;
    @Schema(description = "登录IP")
    @TableField(value = "login_ip")
    private String loginIp;
    @Schema(description = "备注")
    private String remark;
}
