package com.fire.phenix.devops.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(value = "sys_role")
public class SysRole implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String description;

    private Timestamp createTime;

    /**
     * 角色状态
     */
    private Integer status;

    private Integer sort;
    @Column(ignore = true)
    @JsonBackReference
    private Set<SysAccount> accounts;
    @Column(ignore = true)
    private Set<SysMenu> menus;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return String.format("ROLE_%s", this.getName());
    }

}
