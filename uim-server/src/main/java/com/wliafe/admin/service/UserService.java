package com.wliafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wliafe.admin.domain.User;

public interface UserService extends IService<User> {
    User selectByEmail(String email);
}
