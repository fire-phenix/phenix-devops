package com.fire.phenix.devops.security;

import cn.hutool.json.JSONUtil;
import com.fire.phenix.devops.lang.RespCode;
import com.fire.phenix.devops.lang.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fire-phenix
 * @since 2023-11-02
 * 自定义返回结果：没有权限访问时
 */
@Component
public class IAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().println(JSONUtil.parse(Result.failed(RespCode.FORBIDDEN)));
        response.getWriter().flush();
    }
}
