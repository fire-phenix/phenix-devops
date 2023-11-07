package com.fire.phenix.devops.controller;

import com.fire.phenix.devops.entity.SysMenu;
import com.fire.phenix.devops.service.ISysMenuService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 *  控制层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 添加。
     *
     * @param sysMenu 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SysMenu sysMenu) {
        return menuService.save(sysMenu);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return menuService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param sysMenu 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SysMenu sysMenu) {
        return menuService.updateById(sysMenu);
    }



    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public SysMenu getInfo(@PathVariable Serializable id) {
        return menuService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SysMenu> page(Page<SysMenu> page) {
        return menuService.page(page);
    }

}
