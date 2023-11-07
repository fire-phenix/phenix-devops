package com.fire.phenix.devops.controller;

import com.fire.phenix.devops.entity.SysMenu;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.service.ISysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Resource
    private ISysMenuService menuService;

    /**
     * 添加。
     *
     * @param menu 菜单项信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @PreAuthorize("@rbac.hasPermission(authentication)")
    public Long save(@RequestBody SysMenu menu) {
        return menuService.insertMenu(menu);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @PreAuthorize("@rbac.hasPermission(authentication)")
    public boolean remove(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    /**
     * 根据主键更新。
     *
     * @param id   主键ID
     * @param menu 菜单项信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("@rbac.hasPermission(authentication)")
    public boolean update(@PathVariable Long id, @RequestBody SysMenu menu) {
        return menuService.updateMenu(id, menu);
    }

    /**
     * 分页查询。
     *
     * @param num  页码
     * @param size 每页大小
     * @return 分页对象
     */
    @GetMapping("")
    @Operation(summary = "分页查询所有菜单项信息")
    @Parameters({
            @Parameter(name = "num", description = "页码"),
            @Parameter(name = "size", description = "每页大小"),
            @Parameter(name = "condition", description = "查询条件")
    })
    @PreAuthorize("@rbac.hasPermission(authentication)")
    public IPage<SysMenu> page(
            @RequestParam(value = "num", required = false, defaultValue = "1") Integer num,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "condition", required = false) String condition
    ) {
        return menuService.findAllMenus(num, size, condition);
    }

}
