package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.lang.IPage;
import com.mybatisflex.core.service.IService;

/**
 * 服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysAccountService extends IService<SysAccount> {
    SysAccount getAccountByUsername(String username);

    IPage<SysAccount> findAllAccounts(Integer num, Integer size, String condition);

    Long insertAccount(SysAccount account);

    Boolean updateAccount(Long id, SysAccount account);

    Boolean deleteAccount(Long id);
}
