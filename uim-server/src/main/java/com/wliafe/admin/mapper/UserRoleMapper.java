package com.wliafe.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wliafe.admin.domain.UserRole;
import org.apache.ibatis.annotations.Insert;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Insert("insert into uim.uim_user_role(user_id,nick_name,role_id,role_name) values(#{userId},#{nickName},#{roleId},#{roleName})")
    int insertData(String userId, String nickName, String roleId, String roleName);
}
