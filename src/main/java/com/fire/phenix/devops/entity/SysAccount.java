package com.fire.phenix.devops.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * 实体类。
 *
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_account")
public class SysAccount implements Serializable, UserDetails {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private Integer type;

    private String realName;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime loginTime;

    private Integer enable;

    private Integer lock;
    @Column(ignore = true)
    private Collection<? extends GrantedAuthority> authorities;

    @Column(ignore = true)
    @JsonManagedReference
    private Set<SysRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lock == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable == 1;
    }
}
