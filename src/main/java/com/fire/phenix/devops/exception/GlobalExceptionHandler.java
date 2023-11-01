package com.fire.phenix.devops.exception;

import com.fire.phenix.devops.lang.RespCode;
import com.fire.phenix.devops.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result doException(Exception ex) {
        log.info("服务出现的异常:{}", ex.getMessage());
        ex.printStackTrace(System.err);
        return Result.failed(RespCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result doException(HttpMessageNotReadableException ex) {
        log.info("服务出现的异常:{}", ex.getMessage());
        return Result.failed("Http 消息不可读异常");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IAuthenticationException.class)
    public Result doException(IAuthenticationException ex) {
        log.info("认证失败异常:{}", ex.getMessage());
        return Result.failed(RespCode.UNAUTHORIZED.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result doIllegalStateException(IllegalStateException ex) {
        log.info("运行服务出现的IllegalStateException异常:{}", ex.getMessage());
        return Result.failed(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result doIllegalStateException(IllegalArgumentException ex) {
        log.info("运行服务出现的IllegalArgumentException异常:{}", ex.getMessage());
        return Result.failed(ex.getMessage());
    }
}

