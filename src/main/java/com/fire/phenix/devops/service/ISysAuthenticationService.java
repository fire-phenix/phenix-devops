package com.fire.phenix.devops.service;

import com.fire.phenix.devops.model.LoginInfo;
import com.fire.phenix.devops.model.LoginResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
public interface ISysAuthenticationService extends UserDetailsService {
    /**
     * 用户登录请求
     * @param info 登录输入信息
     * @return 登录成功的响应
     */
    LoginResult login(LoginInfo info);

    /**
     * 退出认证
     * @param response 响应体
     * @return 响应消息
     */
    String logout(HttpServletResponse response);

    void verify(HttpServletRequest request, HttpServletResponse response, Long id);
}
