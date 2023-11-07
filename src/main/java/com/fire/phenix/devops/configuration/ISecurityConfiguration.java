package com.fire.phenix.devops.configuration;

import cn.hutool.core.lang.Pair;
import com.fire.phenix.devops.lang.Constant;
import com.fire.phenix.devops.security.IAccessDeniedHandler;
import com.fire.phenix.devops.security.IAuthenticationEntryPoint;
import com.fire.phenix.devops.security.IAuthenticationTokenFilter;
import com.fire.phenix.devops.service.ISysAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author fire-phenix
 * @since 2023-11-06
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ISecurityConfiguration {
    @Resource
    private IAccessDeniedHandler accessDeniedHandler;
    @Resource
    private IAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private IAuthenticationTokenFilter authenticationTokenFilter;
    @Resource
    private ISysAuthenticationService userDetailService;

    /**
     * // 自定义过滤器（图形验证码校验）
     */
    //@Resource
    //private ImageCodeValidateFilter imageCodeValidateFilter;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        // 不需要保护的资源路径允许访问
        for (String url : Constant.WHITES.stream().map(Pair::getValue).collect(Collectors.toList())) {
            registry.antMatchers(url).permitAll();
        }
        // 允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        // 任何请求需要身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //.addFilterBefore(imageCodeValidateFilter, authenticationTokenFilter.getClass())
                .userDetailsService(userDetailService);
        return httpSecurity.build();
    }
}
