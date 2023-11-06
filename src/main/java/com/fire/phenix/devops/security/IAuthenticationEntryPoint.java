package com.fire.phenix.devops.security;

import cn.hutool.json.JSONUtil;
import com.fire.phenix.devops.lang.RespCode;
import com.fire.phenix.devops.lang.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fire-phenix
 * @since 2023-11-06
 * 自定义返回结果：未登录或登录过期
 */
@Component
public class IAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().println(JSONUtil.parse(Result.failed(RespCode.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
