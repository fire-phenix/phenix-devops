package com.fire.phenix.devops.resp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.phenix.devops.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.unnet.traffic.lvs")
public class IResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter param, Class clazz) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter param, MediaType type, Class clazz, ServerHttpRequest req, ServerHttpResponse resp) {
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
        // 如果返回值是String类型，那就手动把Result对象转换成JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        if (body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }
}
