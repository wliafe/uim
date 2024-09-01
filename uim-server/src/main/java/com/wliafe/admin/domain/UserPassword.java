package com.wliafe.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wliafe
 * @date 2023/1/19 12:11
 **/
@Data
@TableName("uim_user_password")
public class UserPassword implements Serializable {
    @TableId(value = "user_id", type = IdType.NONE)
    private String userId;
    private String password;
}
