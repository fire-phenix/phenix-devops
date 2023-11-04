package com.fire.phenix.devops.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
public class LoginInfo {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空!")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空!")
    private String password;
}