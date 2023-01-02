package com.wliafe.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {
    private String phone;
    private String email;
    private String password;
    private String code;
}
