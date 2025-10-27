package com.rixinclass.backend.model.common;


import lombok.Data;

/**
 * @Description 通用返回类
 * @Author DengWenyu
 * @Date 2025/10/27 13:43
 * @Version 1.0
 */

// src/main/java/com/rixinclass/backend/model/common/ApiResult.java
@Data
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> r = new ApiResult<>();
        r.code = 200;
        r.message = "success";
        r.data = data;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> ApiResult<T> fail(int code, String message) {
        ApiResult<T> r = new ApiResult<>();
        r.code = code;
        r.message = message;
        r.timestamp = System.currentTimeMillis();
        return r;
    }
}
