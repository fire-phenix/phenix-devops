package com.fire.phenix.devops.model.dto;

import lombok.Data;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
public class SysAccountDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 备注
     */
    private String remark;
    private Long roleId;
    private String roleName;
}
