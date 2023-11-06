package com.fire.phenix.devops.controller;

import com.fire.phenix.devops.model.LoginInfo;
import com.fire.phenix.devops.model.LoginResult;
import com.fire.phenix.devops.service.ISysAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fire-phenix
 * @since 2023-11-06
 */
@RestController
@Tag(name = "用户认证", description = "用户操作平台时的认证及退出认证")
public class SysAuthenticationController {
    @Resource
    private ISysAuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "用户请求认证")
    public LoginResult login(@RequestParam String code, @RequestParam String key, @RequestBody LoginInfo info) {
        return authenticationService.login(info);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户退出认证")
    public String logout(HttpServletResponse response) {
        return authenticationService.logout(response);
    }

    @GetMapping({"/verify"})
    @Operation(summary = "获取验证码")
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        this.authenticationService.verify(request, response, 1L);
    }
}
