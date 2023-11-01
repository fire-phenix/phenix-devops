package com.fire.phenix.devops.lang;

import lombok.Data;

/**
 * @author fire-phenix
 * @since 2023-11-02
 */
@Data
public class Result {
    private long code;
    private String message;
    private Object data;


    protected Result(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static Result success(Object data) {
        return new Result(RespCode.SUCCESS.getCode(), RespCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static Result failed(String message) {
        return new Result(RespCode.CUSTOM_EXCEPTION.getCode(), message, null);
    }

    public static Result failed(RespCode resp) {
        return new Result(resp.getCode(), resp.getMessage(), null);
    }

    public static Result failed(Integer code, String message) {
        return new Result(code, message, null);
    }
}


