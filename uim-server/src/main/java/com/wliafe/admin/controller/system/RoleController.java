package com.wliafe.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wliafe.admin.domain.Menu;
import com.wliafe.admin.domain.Role;
import com.wliafe.admin.service.RoleService;
import com.wliafe.common.domain.MyResult;
import com.wliafe.common.domain.RoleParameters;
import com.wliafe.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 角色接口
 *
 * @author wliafe
 * @date 2023/1/20 13:30
 **/
@RestController
@RequestMapping("/v1/role")
public class RoleController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RoleService roleService;

    @Operation(summary = "增添角色")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('role:add')")
    @PostMapping("/add")
    public MyResult addRole(@RequestBody RoleParameters roleParameters) {
        if (Objects.isNull(roleParameters)) throw new RuntimeException("角色参数为空");
        Role role = roleParameters.getRole();
        role.setCreateBy(tokenService.getUserId());
        roleService.save(role, roleParameters.getMenuIdList());
        return MyResult.success();
    }

    @Operation(summary = "更新角色")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('role:update')")
    @PutMapping("/update")
    public MyResult updateRole(@RequestBody RoleParameters roleParameters) {
        if (Objects.isNull(roleParameters)) throw new RuntimeException("角色参数为空");
        Role role = roleParameters.getRole();
        role.setUpdateBy(tokenService.getUserId());
        roleService.update(role, roleParameters.getMenuIdList());
        return MyResult.success();
    }

    @Operation(summary = "获取角色信息")
    @Parameters({
            @Parameter(name = "page", example = "1", required = true, in = ParameterIn.PATH),
            @Parameter(name = "size", example = "10", required = true, in = ParameterIn.PATH)
    })
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('role:gets')")
    @GetMapping("/gets/{page}/{size}")
    public MyResult getRoles(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Role> roles = roleService.page(new Page<>(page, size));
        return MyResult.success(roles);
    }

    @Operation(summary = "删除角色")
    @Parameter(name = "roleId", required = true, in = ParameterIn.QUERY)
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('role:deletes')")
    @DeleteMapping("/deletes")
    public MyResult deleteRoles(@RequestParam String roleId) {
        if (Objects.isNull(roleId)) throw new RuntimeException("角色参数为空");
        roleService.removeById(roleId);
        return MyResult.success();
    }
}
