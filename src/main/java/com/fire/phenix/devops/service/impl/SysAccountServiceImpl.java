package com.fire.phenix.devops.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.entity.SysAccountRole;
import com.fire.phenix.devops.lang.PageResp;
import com.fire.phenix.devops.mapper.SysAccountMapper;
import com.fire.phenix.devops.model.dto.PasswordDto;
import com.fire.phenix.devops.model.dto.SysAccountDto;
import com.fire.phenix.devops.model.vo.SysAccountVo;
import com.fire.phenix.devops.service.ISysAccountRoleService;
import com.fire.phenix.devops.service.ISysAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public SysAccount getAccountByUsername(String username) {
        SysAccount account = this.getOne(new QueryWrapper<SysAccount>().lambda().eq(SysAccount::getUsername,username));
        Assert.notNull(account, String.format("用户【%s】不存在，请重试!", username));
        return account;
    }

    @Override
    public PageResp<SysAccountVo> findAccountByUsername(Page<SysAccount> page, String username) {
        Page<SysAccount> accounts = baseMapper.selectPage(page, new QueryWrapper<SysAccount>().lambda().eq(SysAccount::getUsername,username));
        List<SysAccountVo> accountDtos = accounts.getRecords().stream().map(SysAccountVo::new).collect(Collectors.toList());
        return PageResp.<SysAccountVo>builder()
                .list(accountDtos)
                .total(accounts.getTotal())
                .pageNum(accounts.getPages())
                .pageSize(accounts.getSize())
                .build();
    }

    @Resource
    private ISysAccountRoleService accountRoleService;

    @Override
    public Long insertAccount(SysAccountDto accountDto) {
        SysAccount account = BeanUtil.copyProperties(accountDto, SysAccount.class);
        //@Value("${spring.security.default.password}")
        String defaultPassword = "abc123";
        account.setPassword(passwordEncoder.encode(defaultPassword));
        account.setCreatedTime(LocalDateTime.now());
        account.setUpdatedTime(LocalDateTime.now());
        account.setLoginTime(LocalDateTime.now());
        account.setEnabled(1);
        account.setLocked(1);

        if (!this.save(account)) {
            throw new IllegalStateException("添加用户信息失败！");
        }
        boolean result = accountRoleService.save(SysAccountRole.builder().accountId(account.getId()).roleId(accountDto.getRoleId()).build());
        if (!result) {
            throw new IllegalStateException("添加用户关联信息失败！");
        }
        return account.getId();
    }

    @Override
    public Boolean updateAccountByAccountId(Long accountId, SysAccountDto accountDto) {
        SysAccount account = this.getById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许修改");
        }
        if (StrUtil.isNotBlank(accountDto.getRemark())) {
            account.setRemark(accountDto.getRemark());
            Integer count = baseMapper.updateAccountById(account);
            if (count == 0) {
                throw new IllegalStateException("修改用户的备注信息失败！");
            }
        }
        if (accountDto.getRoleId() != null) {
            SysAccountRole accountRole = SysAccountRole.builder().accountId(accountId).roleId(accountDto.getRoleId()).build();
            boolean update = accountRoleService.update(accountRole, new QueryWrapper<SysAccountRole>().lambda().eq(SysAccountRole::getAccountId,accountId));
            if (!update) {
                throw new IllegalStateException("修改用户的角色信息失败！");
            }
        }

        return true;
    }

    @Override
    public Boolean updateAccountPasswordByAccountId(Long accountId, PasswordDto password) {
        SysAccount account = baseMapper.selectById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许修改密码");
        }
        if (!StrUtil.equals(password.getNewPassword(), password.getConfirmPassword())) {
            throw new IllegalStateException("新密码和确认密码不匹配");
        }
        // marches(): 参数1:没有加密的密码；参数2:加密后的密码
        if (!passwordEncoder.matches(password.getOldPassword(), account.getPassword())) {
            throw new IllegalStateException("旧密码和原始密码不匹配!");
        }

        if (passwordEncoder.matches(password.getNewPassword(), account.getPassword())) {
            throw new IllegalStateException("新密码不能和原始密码相同");
        }
        account.setPassword(passwordEncoder.encode(password.getNewPassword()));

        return baseMapper.updateAccountById(account) > 0;
    }

    @Override
    public Boolean deleteAccountByAccountId(Long accountId) {
        SysAccount account = this.getById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许删除");
        }

        if (!this.removeById(accountId)) {
            throw new IllegalStateException("删除失败！");
        }
        return true;
    }
}
