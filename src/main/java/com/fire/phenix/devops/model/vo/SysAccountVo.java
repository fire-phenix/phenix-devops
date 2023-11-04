package com.fire.phenix.devops.model.vo;

import com.fire.phenix.devops.entity.SysAccount;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
public class SysAccountVo {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 图像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账户类型
     */
    private int type;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    private Long roleId;

    /**
     * 是否可用（0表示不可用；1表示可用）
     */
    private Integer enabled;

    /**
     * 是否被锁（0表示被锁；1表示可用）
     */
    private Integer locked;

    private String roleName;

    public SysAccountVo(SysAccount account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.icon = account.getIcon();
        this.email = account.getEmail();
        this.type = account.getType();
        this.realName = account.getRealName();
        this.remark = account.getRemark();
        this.createdTime = account.getCreatedTime();
        this.updatedTime = account.getUpdatedTime();
        this.loginTime = account.getLoginTime();
        this.enabled = account.isEnabled() ? 1 : 0;
        this.locked = account.isAccountNonLocked() ? 1 : 0;
        this.roleId = account.getRoleId();
        this.roleName = account.getRoleName();
    }
}
