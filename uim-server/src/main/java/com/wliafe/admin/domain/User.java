package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("uim_user")
public class User {
    @TableId(value = "user_id", type = IdType.NONE)
    private String userId;
    @TableField(value = "nick_name")
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String name;
    private Integer sex;
    private Date birth;
    @TableField(value = "id_number")
    private String idNumber;
    private String province;
    private String city;
    private String district;
    private String address;
    private String status;
    @TableField(value = "del_flag")
    @TableLogic(value = "0", delval = "1")
    private String delFlag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "login_date")
    private Date loginDate;
    @TableField(value = "login_ip")
    private String loginIp;
    private String remark;
}
