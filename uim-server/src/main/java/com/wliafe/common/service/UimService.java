package com.wliafe.common.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * uim基本信息
 *
 * @author wliafe
 * @date 2023/1/12 0:17
 **/
@Data
@Service
public class UimService {
    @Value("${uim.title:用户信息管理系统}")
    private String title;
    @Value("${uim.description:记录并管理用户基本信息}")
    private String description;
    @Value("${uim.version:1.0}")
    private String version;
    @Value("${uim.author.name:wliafe}")
    private String name;
    @Value("${uim.author.url:https://blog.csdn.net/weixin_49777720}")
    private String url;
    @Value("${uim.author.email:wliafe@qq.com}")
    private String email;
}
