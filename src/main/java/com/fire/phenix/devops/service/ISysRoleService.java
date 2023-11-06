package com.fire.phenix.devops.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fire.phenix.devops.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 *  服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysRoleService extends IService<SysRole> {
    List<SysRole> findAllRoles();

    /**
     * 根据用户ID查询角色信息
     * @param accountId 用户ID
     * @return 角色信息
     */
    Set<String> findRolesByAccountId(Long accountId);
}
