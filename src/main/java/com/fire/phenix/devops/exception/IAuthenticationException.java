package com.fire.phenix.devops.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
public class IAuthenticationException extends AuthenticationException {
    private String message;

    public IAuthenticationException(String msg) {
        super(msg);
        this.message = msg;
    }


    @Override
    public String getMessage() {
        return message;
    }
}