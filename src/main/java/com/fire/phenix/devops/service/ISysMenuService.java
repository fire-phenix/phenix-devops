package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysMenu;
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
}
