package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysMenu;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.model.Router;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> findMenusByAccountId(Long accountId);

    /**
     * 获取用户具有权限的路由信息
     *
     * @return 路由列表
     */
    List<Router> getRouters();

    /**
     * 分页查询菜单项信息
     *
     * @param num  页码
     * @param size 每页大小
     * @param condition  查询条件
     * @return 分页结果
     */
    IPage<SysMenu> findAllMenus(Integer num, Integer size, String condition);

    Long insertMenu(SysMenu menu);

    Boolean updateMenu(Long id, SysMenu menu);

    Boolean deleteMenu(Long id);

    List<String> findUrlByUsername(String username);
}
