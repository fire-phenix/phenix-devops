package com.fire.phenix.devops.service.impl;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.mapper.SysAccountMapper;
import com.fire.phenix.devops.service.ISysAccountService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.fire.phenix.devops.entity.table.SysAccountTableDef.SYS_ACCOUNT;

/**
 * 服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {
    @Override
    public SysAccount getAccountByUsername(String username) {
        SysAccount account = this.getOne(QueryWrapper.create().where(SYS_ACCOUNT.USERNAME.eq(username)));
        Assert.notNull(account, String.format("用户【%s】不存在，请重试!", username));
        return account;
    }
}
