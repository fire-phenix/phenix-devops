package com.fire.phenix.devops.service.impl;

import com.fire.phenix.devops.entity.SysAccount;
import com.fire.phenix.devops.entity.SysResource;
import com.fire.phenix.devops.exception.IAuthenticationException;
import com.fire.phenix.devops.fastmap.ExpireCallback;
import com.fire.phenix.devops.fastmap.FastMap;
import com.fire.phenix.devops.model.LoginInfo;
import com.fire.phenix.devops.model.LoginResult;
import com.fire.phenix.devops.service.ISysAccountService;
import com.fire.phenix.devops.service.ISysAuthenticationService;
import com.fire.phenix.devops.service.ISysResourceService;
import com.fire.phenix.devops.service.ISysRoleService;
import com.fire.phenix.devops.utils.RandomValidateCodeUtil;
import com.fire.phenix.devops.utils.TokenProviderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.header.CacheControlServerHttpHeadersWriter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Slf4j
@Service
public class SysAuthenticationServiceImpl implements ISysAuthenticationService {
    @Resource
    private ISysAccountService accountService;
    @Resource
    private ISysResourceService resourceService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ISysRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        SysAccount account = accountService.getAccountByUsername(username);
        if (account != null) {
            List<SysResource> resources = resourceService.findResourcesByAccountId(account.getId());
            List<SimpleGrantedAuthority> authorities = resources.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                    .collect(Collectors.toList());
            account.setAuthorities(authorities);
            return account;
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public LoginResult login(LoginInfo info) {
        SysAccount account = accountService.getAccountByUsername(info.getUsername());

        List<SysResource> resources = resourceService.findResourcesByAccountId(account.getId());
        List<SimpleGrantedAuthority> authorities = resources.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                .collect(Collectors.toList());
        account.setAuthorities(authorities);
        String password = info.getPassword();

//        log.info("private security:{}", secure);
//        RSA rsa = new RSA(secure, null);
        // Base64解码转成字符串类型
//        String decode = new String(Base64.getDecoder().decode(info.getPassword()), StandardCharsets.UTF_8);
        // 解密数据
//        String password = rsa.decryptStr(decode, KeyType.PrivateKey, StandardCharsets.UTF_8);
        log.info("password:{}", password);
        String password1 = account.getPassword();
        log.info("password1:{}", password1);
        log.info("密码对比结果:{}",passwordEncoder.matches(password, account.getPassword()));
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IAuthenticationException("用户密码不正确!");
        }
        if (!account.isEnabled() || !account.isAccountNonLocked()) {
            throw new IllegalStateException("账户不可用或已被锁!");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LoginResult.builder()
                .username(info.getUsername())
                .realName(account.getRoleName())
                .email(account.getEmail())
                .icon(account.getIcon())
                .remark(account.getRemark())
                .token(TokenProviderUtil.token(info.getUsername()))
                .build();
    }

    @Override
    public String logout(HttpServletResponse response) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("推出认证失败，原因：{}", ex.getMessage());
            throw new IllegalStateException("请求失败");
        }
        return "success";
    }

    @Resource
    private FastMap<String, String> fastMap;

    @Override
    public void verify(HttpServletRequest request, HttpServletResponse response, Long id) {
        // 验证码的过期时间
        long expiredTime = 3 * 60 * 1000;
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", CacheControlServerHttpHeadersWriter.PRAGMA_VALUE);
        response.setDateHeader("Expires", System.currentTimeMillis() + expiredTime);
        response.setHeader("Access-Control-Expose-Headers", "key");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        response.setHeader("key", uuid);
        try {
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            String randomCode = randomValidateCode.getRandomCode(request, response);
            fastMap.put(uuid, randomCode);
            // 设置过期的回调事件
            fastMap.expire(uuid, expiredTime, (ExpireCallback) (key, val) -> fastMap.remove(uuid));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
