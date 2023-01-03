package com.wliafe.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wliafe.admin.domain.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select distinct menu.perms " +
            "from uim.uim_user_role user_role " +
            "left join uim.uim_role role on user_role.role_id = role.role_id " +
            "left join uim.uim_role_menu role_menu on user_role.role_id=role_menu.role_id " +
            "left join uim.uim_menu menu on menu.menu_id = role_menu.menu_id " +
            "where user_id = #{userId} and role.status = 0 and menu.status = 0")
    List<String> getAuthoritiesByUserId(String userId);
}
