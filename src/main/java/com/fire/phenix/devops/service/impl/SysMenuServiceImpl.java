package com.fire.phenix.devops.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.fire.phenix.devops.common.SelectCommon;
import com.fire.phenix.devops.entity.SysMenu;
import com.fire.phenix.devops.entity.SysRole;
import com.fire.phenix.devops.lang.IPage;
import com.fire.phenix.devops.mapper.SysMenuMapper;
import com.fire.phenix.devops.model.Router;
import com.fire.phenix.devops.model.RouterType;
import com.fire.phenix.devops.service.ISysMenuService;
import com.fire.phenix.devops.service.ISysRoleService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fire.phenix.devops.entity.table.SysAccountRoleTableDef.SYS_ACCOUNT_ROLE;
import static com.fire.phenix.devops.entity.table.SysAccountTableDef.SYS_ACCOUNT;
import static com.fire.phenix.devops.entity.table.SysMenuTableDef.SYS_MENU;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Resource
    private ISysRoleService roleService;

    @Override
    public List<SysMenu> findMenusByAccountId(Long accountId) {
        return this.list(QueryWrapper.create()
                .select("m.*").from(SYS_MENU.as("m"))
                .leftJoin(SYS_ROLE_MENU).on(SYS_MENU.ID.eq(SYS_ROLE_MENU.MENU_ID))
                .leftJoin(SYS_ACCOUNT_ROLE).on(SYS_ACCOUNT_ROLE.ROLE_ID.eq(SYS_ROLE_MENU.ROLE_ID))
                .where(SYS_ACCOUNT_ROLE.ACCOUNT_ID.ge(accountId)));
    }

    @Override
    public List<Router> getRouters() {
        final String[] whiteRoles = new String[]{"SUPER_ADMIN", "SUPER_DEV", "DEV", "ADMIN"};
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        List<SysRole> roles = roleService.findRolesByUsername(authentication);
        Assert.notEmpty(roles, String.format("该用户【%s】没有角色信息", authentication));
        boolean isContains = Stream.of(whiteRoles).anyMatch(role -> roles.stream().map(SysRole::getCode).map(String::toUpperCase).collect(Collectors.toList()).contains(role.toUpperCase()));
        List<SysMenu> menus;
        if (isContains) {
            menus = mapper.selectAll();
        } else {
            menus = this.findMenusByRoleIds(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        }
        // 过滤目录和菜单
        menus = menus.stream()
                .filter(menu -> ListUtil.of(RouterType.DIR.getValue(), RouterType.MENU.getValue()).contains(menu.getType()))
                .sorted(Comparator.comparing(SysMenu::getSort))
                .collect(Collectors.toList());
        return this.recursionFillRouter(0L, new HashSet<>(menus));
    }

    @Override
    public IPage<SysMenu> findAllMenus(Integer num, Integer size, String condition) {
        return new IPage<>(new SelectCommon<SysMenu>().findAll(num, size, condition, this));
    }

    @Override
    public Long insertMenu(SysMenu menu) {
        menu.setCreateTime(LocalDateTime.now());
        if (!this.save(menu)) {
            throw new IllegalStateException("添加菜单项失败");
        }
        return menu.getId();
    }

    @Override
    public Boolean updateMenu(Long id, SysMenu menu) {
        if (mapper.updatePartFieldById(id, menu) < 1) {
            throw new IllegalStateException("修改菜单项信息失败");
        }
        return true;
    }

    @Override
    public Boolean deleteMenu(Long id) {
        List<SysRole> roles = roleService.list(QueryWrapper.create().select("r.*").from(SYS_ROLE.as("r")).leftJoin(SYS_ROLE_MENU).as("rm").on(SYS_ROLE.ID.eq(SYS_ROLE_MENU.ROLE_ID)).where(SYS_ROLE_MENU.MENU_ID.eq(id)));
        if (CollectionUtil.isNotEmpty(roles)) {
            throw new IllegalStateException(String.format("当前菜单项正在被角色【%s】使用，不允许删除", roles.stream().map(SysRole::getName).collect(Collectors.toSet())));
        }
        if (!this.removeById(id)) {
            throw new IllegalStateException("删除菜单失败");
        }
        return true;
    }

    @Override
    public List<String> findUrlByUsername(String username) {
        List<SysMenu> menus = this.list(
                QueryWrapper.create().select("m.url").from(SYS_MENU.as("m"))
                        .leftJoin(SYS_ROLE_MENU).on(SYS_MENU.ID.eq(SYS_ROLE_MENU.MENU_ID))
                        .leftJoin(SYS_ACCOUNT_ROLE).on(SYS_ROLE_MENU.ROLE_ID.eq(SYS_ACCOUNT_ROLE.ROLE_ID))
                        .leftJoin(SYS_ACCOUNT).on(SYS_ACCOUNT.ID.eq(SYS_ACCOUNT_ROLE.ACCOUNT_ID))
                        .where(SYS_ACCOUNT.USERNAME.eq(username))
        );
        return menus.stream().map(SysMenu::getUrl).collect(Collectors.toList());
    }

    private List<SysMenu> findMenusByRoleIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return this.list(
                QueryWrapper.create().select("m.*").from(SYS_MENU.as("m"))
                        .leftJoin(SYS_ROLE_MENU).on(SYS_ROLE_MENU.MENU_ID.eq(SYS_MENU.ID))
                        .where(SYS_ROLE_MENU.ROLE_ID.in(ids))
        );
    }

    /**
     * 递归填补路由器
     */
    private List<Router> recursionFillRouter(Long parentId, Set<SysMenu> menus) {
        List<Router> routers = menus.stream().filter(menu -> parentId.equals(menu.getParentId())).map(Router::new).collect(Collectors.toList());
        routers.forEach(router -> router.setChildren(this.recursionFillRouter(router.getId(), menus)));
        return routers;
    }
}
