package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.lang.IPage;
import com.mybatisflex.core.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysRoleService extends IService<SysRole> {
    Set<String> findRolesByAccountId(Long accountId);

    List<SysRole> findRolesByUsername(String username);

    /**
     * 分页查询所有角色信息
     *
     * @param num       页码
     * @param size      每页大小
     * @param condition 查询条件
     * @return 分页对象
     */
    IPage<SysRole> findAllRoles(Integer num, Integer size, String condition);

    Long insertRole(SysRole menu);

    Boolean updateRole(Long id, SysRole menu);

    Boolean deleteRole(Long id);

    /**
     * 为角色分配菜单
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID
     * @return true|false
     */
    Boolean assignmentMenu(Long roleId, List<Long> menuIds);
}
