package com.fire.phenix.devops.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fire.phenix.devops.common.SelectCommon;
import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.entity.SysRoleMenu;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.mapper.SysRoleMapper;
import com.fire.phenix.devops.service.ISysAccountService;
import com.fire.phenix.devops.service.ISysRoleMenuService;
import com.fire.phenix.devops.service.ISysRoleService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fire.phenix.devops.entity.table.SysAccountRoleTableDef.SYS_ACCOUNT_ROLE;
import static com.fire.phenix.devops.entity.table.SysAccountTableDef.SYS_ACCOUNT;
import static com.fire.phenix.devops.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.fire.phenix.devops.entity.table.SysRoleTableDef.SYS_ROLE;

/**
 * 服务层实现。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Log4j2
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private ISysAccountService accountService;

    @Resource
    private ISysRoleMenuService roleMenuService;

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

    @Override
    public IPage<SysRole> findAllRoles(Integer num, Integer size, String condition) {
        return new IPage<>(new SelectCommon<SysRole>().findAll(num, size, condition, this));
    }

    @Override
    public Long insertRole(SysRole role) {
        role.setCreateTime(LocalDateTime.now());
        if (!this.save(role)) {
            throw new IllegalStateException("添加角色失败");
        }
        return role.getId();
    }

    @Override
    public Boolean updateRole(Long id, SysRole role) {
        if (mapper.updatePartFieldById(id, role) < 1) {
            throw new IllegalStateException("修改角色失败");
        }
        return true;
    }

    @Override
    public Boolean deleteRole(Long id) {
        List<SysAccount> accounts = accountService.list(
                QueryWrapper.create().select("a.*").from(SYS_ACCOUNT.as("a")).leftJoin(SYS_ACCOUNT_ROLE).on(SYS_ACCOUNT_ROLE.ACCOUNT_ID.eq(SYS_ACCOUNT.ID)).where(SYS_ACCOUNT_ROLE.ROLE_ID.eq(id))
        );
        if (CollectionUtil.isNotEmpty(accounts)) {
            throw new IllegalStateException(String.format("该角色正在被用户【%s】使用，不允许被删除", accounts.stream().map(SysAccount::getRealName).collect(Collectors.toSet())));
        }
        if (!this.removeById(id)) {
            throw new IllegalStateException("删除角色失败");
        }

        if (!roleMenuService.remove(QueryWrapper.create().where(SysRoleMenu::getRoleId).eq(id))) {
            throw new IllegalStateException("删除角色信息失败");
        }
        return true;
    }

    @Override
    public Boolean assignmentMenu(Long roleId, List<Long> menuIds) {
        // 删除原有菜单项
        if (roleMenuService.remove(QueryWrapper.create().where(SYS_ROLE_MENU.ROLE_ID.eq(roleId)))) {
            throw new IllegalStateException("分配菜单项失败");
        }
        // 新增角色菜单项关联关系
        List<SysRoleMenu> roleMenus = new LinkedList<>();
        for (Long menuId : menuIds) {
            roleMenus.add(SysRoleMenu.builder().roleId(roleId).menuId(menuId).build());
        }
        if (!roleMenuService.saveBatch(roleMenus)) {
            throw new IllegalStateException("分配菜单项失败");
        }
        return null;
    }
}
