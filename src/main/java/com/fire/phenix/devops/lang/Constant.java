package com.fire.phenix.devops.lang;

import cn.hutool.core.lang.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fire-phenix
 * @since 2023-11-06
 */
@Component
public class Constant {
    // Session 中存储图形验证码的属性名
    public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    private static final String V1 = "/api/devops";

    public static final List<Pair<HttpMethod, String>> WHITES = new LinkedList<>();

    static {
        WHITES.add(Pair.of(HttpMethod.POST, "/login"));
        WHITES.add(Pair.of(HttpMethod.POST, "/logout"));
        WHITES.add(Pair.of(HttpMethod.GET, "/verify"));
        WHITES.add(Pair.of(HttpMethod.GET, "/favicon.ico"));
        WHITES.add(Pair.of(HttpMethod.GET, "/swagger-ui.html"));
        WHITES.add(Pair.of(HttpMethod.GET, "/swagger-ui/**"));
        WHITES.add(Pair.of(HttpMethod.GET, "/v3/**"));
    }
}
