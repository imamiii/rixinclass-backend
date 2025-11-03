package com.rixinclass.backend.utils;

import lombok.Data;

/**
 * 统一响应结果工具类
 */
@Data
public class Result<T> {
    private int code; // 状态码：200成功，401未授权，500错误
    private String msg; // 提示信息
    private T data; // 响应数据
    private long timestamp; // 时间戳

    // 私有构造器，仅通过静态方法创建实例
    private Result(int code, String msg, T data, long timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    // 成功响应（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null, System.currentTimeMillis());
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data, System.currentTimeMillis());
    }

    // 错误响应
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null, System.currentTimeMillis());
    }

    // 未授权响应
    public static <T> Result<T> unAuth(String msg) {
        return new Result<>(401, msg, null, System.currentTimeMillis());
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "success", data, System.currentTimeMillis());
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null, System.currentTimeMillis());
    }
}