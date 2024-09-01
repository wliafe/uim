package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wliafe.admin.domain.Role;
import com.wliafe.admin.domain.RoleMenu;
import com.wliafe.admin.mapper.MenuMapper;
import com.wliafe.admin.mapper.RoleMapper;
import com.wliafe.admin.mapper.RoleMenuMapper;
import com.wliafe.admin.service.RoleService;
import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author wliafe
 * @date 2023/1/12 0:02
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    public void save(Role role, List<String> menuIdList) {
        //角色信息验证及插入
        if (Objects.isNull(role)) throw new RuntimeException("角色信息为空");
        role.setRoleId(UUIDUtil.getUUID());
        roleMapper.insert(role);
        //角色权限信息验证及插入
        if (Objects.isNull(menuIdList)) return;
        for (String menuId : menuIdList)
            if (Objects.isNull(menuMapper.selectById(menuId))) throw new RuntimeException("权限不存在");
            else roleMenuMapper.insert(new RoleMenu(role.getRoleId(), menuId));
    }

    @Override
    public void update(Role role, List<String> menuIdList) {
        //角色信息更新
        if (Objects.isNull(role)) throw new RuntimeException("角色信息为空");
        roleMapper.updateById(role);
        //权限信息更新
        if (Objects.isNull(menuIdList)) return;
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, role.getRoleId());
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper);
        for (RoleMenu roleMenu : roleMenus)
            if (menuIdList.contains(roleMenu.getMenuId())) menuIdList.remove(roleMenu.getMenuId());
            else roleMenuMapper.deleteById(new RoleMenu(role.getRoleId(), roleMenu.getMenuId()));
        for (String menuId : menuIdList)
            if (Objects.isNull(menuMapper.selectById(menuId))) throw new RuntimeException("权限不存在");
            else roleMenuMapper.insert(new RoleMenu(role.getRoleId(), menuId));
    }
}
