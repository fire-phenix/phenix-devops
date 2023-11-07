package com.fire.phenix.devops.service.impl;

import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.mapper.SysRoleMapper;
import com.fire.phenix.devops.service.ISysRoleService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fire.phenix.devops.entity.table.SysAccountRoleTableDef.SYS_ACCOUNT_ROLE;
import static com.fire.phenix.devops.entity.table.SysAccountTableDef.SYS_ACCOUNT;
import static com.fire.phenix.devops.entity.table.SysRoleTableDef.SYS_ROLE;

/**
 * 服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public Set<String> findRolesByAccountId(Long accountId) {
        List<SysRole> roles = this.list(QueryWrapper.create().select().from(SYS_ROLE).leftJoin(SYS_ACCOUNT_ROLE).on(SYS_ACCOUNT_ROLE.ROLE_ID.eq(SYS_ROLE.ID)).where(SYS_ACCOUNT_ROLE.ACCOUNT_ID.eq(accountId)));
        return roles.stream().map(SysRole::getName).collect(Collectors.toSet());
    }

    @Override
    public List<SysRole> findRolesByUsername(String username) {
        return this.list(
                QueryWrapper.create().select("r.*").from(SYS_ROLE.as("r"))
                        .leftJoin(SYS_ACCOUNT_ROLE).on(SYS_ACCOUNT_ROLE.ROLE_ID.eq(SYS_ROLE.ID))
                        .leftJoin(SYS_ACCOUNT).on(SYS_ACCOUNT.ID.eq(SYS_ACCOUNT_ROLE.ACCOUNT_ID))
                        .where(SYS_ACCOUNT.USERNAME.eq(username))
        );
    }
}
