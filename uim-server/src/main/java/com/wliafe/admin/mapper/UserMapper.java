package com.wliafe.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wliafe.admin.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * from uim.uim_user where email= #{email}")
    User selectByEmail(String email);
}