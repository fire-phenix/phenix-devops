package com.fire.phenix.devops.controller;

import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.service.ISysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 控制层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private ISysRoleService roleService;

    /**
     * 添加。
     *
     * @param role 角色信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public Long save(@RequestBody SysRole role) {
        return roleService.insertRole(role);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return roleService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param role 角色信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody SysRole role) {
        return roleService.updateRole(id, role);
    }

    /**
     * 分页查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("")
    @Operation(summary = "分页查询所有角色信息")
    @Parameters({
            @Parameter(name = "num", description = "页码"),
            @Parameter(name = "size", description = "每页大小"),
            @Parameter(name = "condition", description = "查询条件")
    })
    public IPage<SysRole> list(
            @RequestParam(value = "num", required = false, defaultValue = "1") Integer num,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "condition", required = false) String condition
    ) {
        return roleService.findAllRoles(num, size, condition);
    }
}
