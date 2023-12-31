package com.fire.phenix.devops.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Slf4j
public class TokenProviderUtil {
    public static final Integer OFFSET_HOUR =6;
    public static final String HEADER = "Authorization";
    public static final String JWT_AUTH_PREFIX = "Bearer ";
    public static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final byte[] KEY = "1234567890".getBytes();

    public static String token(String username) {
        return JWT.create()
                // 设置签发时间
                .setIssuedAt(DateUtil.date())
                // 设置过期时间
                .setExpiresAt(DateUtil.offsetHour(DateUtil.date(), OFFSET_HOUR))
                // 设置签发时间
                .setNotBefore(DateUtil.date())
                .setIssuer("devil-idiots")
                .setSubject("traffic-lvs")
                .setJWTId(UUID.randomUUID().toString())
                .setPayload("username", username)
                .setKey(KEY)
                .sign();
    }

    public static Boolean validate(String token) {
        JWTValidator jwtValidator = JWTValidator.of(token)
                .validateAlgorithm(JWTSignerUtil.hs256(KEY))
                // leeway:容忍空间，单位：秒
                .validateDate(DateUtil.date(), 5);
        return jwtValidator != null;
    }

    public static String getAccount(String token){
        final JWT jwt = JWTUtil.parseToken(token);
        return (String) jwt.getPayload("username");
    }

    /**
     * 解析登录时的 jwt token
     *
     * @param token token
     * @return pair
     */
    public static Pair<String, String> decodeBasicAuthToken(String token) {
        String plain = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        // split() 将提供的文本拆分为具有最大长度、指定分隔符的数组
        String[] userAndPassword = StringUtils.split(plain, ":", 2);
        if (userAndPassword.length != 2) {
            throw new IllegalStateException("bad authentication token");
        }
        return Pair.of(userAndPassword[0], userAndPassword[1]);
    }

    public static Pair<String, String> getAuthToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER);

        // token验证
        if (StringUtils.startsWith(header, JWT_AUTH_PREFIX)) {
            return Pair.of(JWT_AUTH_PREFIX, StringUtils.removeStart(header, JWT_AUTH_PREFIX));
        }
        // 密码验证
        if (StringUtils.startsWith(header, BASIC_AUTH_PREFIX)) {
            return Pair.of(BASIC_AUTH_PREFIX, StringUtils.removeStart(header, BASIC_AUTH_PREFIX));
        }
        String[] seg = StringUtils.split(header, " ");
        if (seg != null && seg.length == 2) {
            return Pair.of(seg[0], seg[1]);
        }
        log.info("bad authentication token [{}], {} => {} {}", header, request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
        throw new IllegalStateException("bad authentication token");
    }
}