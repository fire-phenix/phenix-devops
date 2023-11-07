package com.fire.phenix.devops.security;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.service.ISysAccountService;
import com.fire.phenix.devops.service.ISysMenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author fire-phenix
 * @since 2023-11-07
 */
@Log4j2
@Component("rbac")
public class IRbacConfiguration {
    @Resource
    private ISysAccountService accountService;

    @Resource
    private ISysMenuService menuService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(Authentication authentication) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        log.info("request: {}", request);
        SysAccount account = accountService.findAccountByUsername(authentication.getName());
        log.info("user: {}", account);
        String uri = request.getRequestURI();
        log.info("uri :{}", uri);
        log.info("url :{}", request.getRequestURL());
        log.info("method :{}", request.getMethod());
        log.info("session :{}", request.getSession());
        log.info("host :{}", request.getRemoteHost());
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(uri);
        log.info("authorityList: {}", authorityList);
        List<String> urls = menuService.findUrlByUsername(authentication.getName());
        log.info("urls: {}", urls);
        return urls.stream().anyMatch(url -> antPathMatcher.match(url, request.getRequestURI()));
    }
}

