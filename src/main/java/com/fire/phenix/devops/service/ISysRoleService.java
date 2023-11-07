package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysRole;
import com.mybatisflex.core.service.IService;

import java.util.List;
import java.util.Set;

/**
 *  服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysRoleService extends IService<SysRole> {
    Set<String> findRolesByAccountId(Long accountId);

    List<SysRole> findRolesByUsername(String username);
}
