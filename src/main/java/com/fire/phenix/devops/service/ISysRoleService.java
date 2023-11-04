package com.fire.phenix.devops.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fire.phenix.devops.entity.SysRole;

import java.util.List;

/**
 *  服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysRoleService extends IService<SysRole> {
    List<SysRole> findAllRoles();

}
