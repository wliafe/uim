package com.wliafe.admin.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wliafe.admin.domain.Menu;
import com.wliafe.admin.service.MenuService;
import com.wliafe.common.domain.MyResult;
import com.wliafe.common.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 菜单接口
 *
 * @author wliafe
 * @date 2023/1/20 13:22
 **/
@RestController
@RequestMapping("/v1/menu")
public class MenuController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MenuService menuService;

    @Operation(summary = "增添权限")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('menu:add')")
    @PostMapping("/add")
    public MyResult addMenu(@RequestBody Menu menu) {
        if (Objects.isNull(menu)) throw new RuntimeException("权限参数为空");
        menu.setCreateBy(tokenService.getUserId());
        menuService.save(menu);
        return MyResult.success();
    }

    @Operation(summary = "更新权限")
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('menu:update')")
    @PutMapping("/update")
    public MyResult updateMenu(@RequestBody Menu menu) {
        if (Objects.isNull(menu)) throw new RuntimeException("权限参数为空");
        menu.setUpdateBy(tokenService.getUserId());
        menuService.updateById(menu);
        return MyResult.success();
    }

    @Operation(summary = "获取权限信息")
    @Parameters({
            @Parameter(name = "page", example = "1", required = true, in = ParameterIn.PATH),
            @Parameter(name = "size", example = "10", required = true, in = ParameterIn.PATH)
    })
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('menu:gets')")
    @GetMapping("/gets/{page}/{size}")
    public MyResult getMenus(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Menu> menus = menuService.page(new Page<>(page, size));
        return MyResult.success(menus);
    }

    @Operation(summary = "删除权限")
    @Parameter(name = "menuId", required = true, in = ParameterIn.QUERY)
    @ApiResponse(responseCode = "200", description = "请求成功")
    @PreAuthorize("@ss.hasAuthority('menu:deletes')")
    @DeleteMapping("/deletes")
    public MyResult deleteMenus(@RequestParam String menuId) {
        if (Objects.isNull(menuId)) throw new RuntimeException("权限参数为空");
        menuService.removeById(menuId);
        return MyResult.success();
    }
}
