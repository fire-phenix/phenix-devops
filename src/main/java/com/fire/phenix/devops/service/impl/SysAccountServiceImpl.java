package com.fire.phenix.devops.service.impl;

import com.fire.phenix.devops.common.SelectCommon;
import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.mapper.SysAccountMapper;
import com.fire.phenix.devops.service.ISysAccountService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

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

    @Override
    public IPage<SysAccount> findAllAccounts(Integer num, Integer size, String condition) {
        return new IPage<>(new SelectCommon<SysAccount>().findAll(num, size, condition, this));
    }

    @Override
    public Long insertAccount(SysAccount account) {
        account.setCreateTime(LocalDateTime.now());
        if (!this.save(account)) {
            throw new IllegalStateException("添加菜单项失败");
        }
        return account.getId();
    }

    @Override
    public Boolean updateAccount(Long id, SysAccount account) {
        if (mapper.updatePartFieldById(id, account) < 1) {
            throw new IllegalStateException("修改菜单项信息失败");
        }
        return true;
    }

    @Override
    public Boolean deleteAccount(Long id) {
        if (!this.removeById(id)) {
            throw new IllegalStateException("删除菜单失败");
        }
        return true;
    }
}
