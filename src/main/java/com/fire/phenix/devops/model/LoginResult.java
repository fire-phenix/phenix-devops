package com.fire.phenix.devops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private String username;
    private String realName;
    private String email;
    private String icon;
    private String remark;
    private Set<String> roles;
    private Set<String> permissions;
    private String token;
}
