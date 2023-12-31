package com.fire.phenix.devops.security;

import com.fire.phenix.devops.entity.SysAccount;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Component
public class IAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;

    public IAuthenticationManager(UserDetailsService userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails ud = userDetailService.loadUserByUsername(authentication.getName());
        assert ud instanceof SysAccount;
        if (passwordEncoder.matches((String) authentication.getCredentials(), ud.getPassword())) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud.getUsername(), ud.getPassword());
            auth.setDetails(ud);
            return auth;
        }
        return null;
    }
}

