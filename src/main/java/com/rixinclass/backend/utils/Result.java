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

    // 私有构造器，仅通过静态方法创建实例
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功响应（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 错误响应
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    // 未授权响应（补充此方法解决报错）
    public static <T> Result<T> unAuth(String msg) {
        return new Result<>(401, msg, null);
    }
}