package com.fire.phenix.devops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author fire-phenix
 * @since 2023-11-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private String username;
    private String realName;
    private String email;
    private String remark;
    private String icon;
    private Set<String> roles;
    private Set<String> permissions;
    private String token;
}
