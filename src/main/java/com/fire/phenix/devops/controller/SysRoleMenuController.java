package com.fire.phenix.devops.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.fire.phenix.devops.entity.SysRoleMenu;
import com.fire.phenix.devops.service.ISysRoleMenuService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 *  控制层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;

    /**
     * 添加。
     *
     * @param sysRoleMenu 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SysRoleMenu sysRoleMenu) {
        return iSysRoleMenuService.save(sysRoleMenu);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return iSysRoleMenuService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param sysRoleMenu 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SysRoleMenu sysRoleMenu) {
        return iSysRoleMenuService.updateById(sysRoleMenu);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SysRoleMenu> list() {
        return iSysRoleMenuService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public SysRoleMenu getInfo(@PathVariable Serializable id) {
        return iSysRoleMenuService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SysRoleMenu> page(Page<SysRoleMenu> page) {
        return iSysRoleMenuService.page(page);
    }

}