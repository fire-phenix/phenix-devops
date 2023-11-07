package com.fire.phenix.devops.service;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.lang.IPage;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 服务层。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysAccountService extends IService<SysAccount> {
    SysAccount findAccountByUsername(String username);

    IPage<SysAccount> findAllAccounts(Integer num, Integer size, String condition);

    Long insertAccount(SysAccount account);

    Boolean updateAccount(Long id, SysAccount account);

    Boolean deleteAccount(Long id);

    /**
     * 为用户分配角色信息
     * @param accountId 账户ID
     * @param roleIds 角色ID
     * @return true|false
     */
    Boolean assignmentMenu(Long accountId, List<Long> roleIds);
}
