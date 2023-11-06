package com.fire.phenix.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.mapper.SysRoleMapper;
import com.fire.phenix.devops.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 *  服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Override
    public List<SysRole> findAllRoles() {
        return this.list();
    }

    @Override
    public Set<String> findRolesByAccountId(Long accountId) {

        return null;
    }
}
