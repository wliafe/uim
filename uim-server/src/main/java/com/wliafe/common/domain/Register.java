package com.wliafe.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Register implements Serializable {
    private String phone;
    private String email;
    private String roleId;
    private String code;
}
