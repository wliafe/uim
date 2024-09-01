package com.wliafe.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wliafe.admin.domain.User;

import java.util.List;

/**
 * @author wliafe
 * @date 2023/1/12 0:02
 **/
public interface UserService extends IService<User> {
    void register(User user, List<String> roleIdList);

    void update(User user, List<String> roleIdList);

    User getByEmail(String email);
}
