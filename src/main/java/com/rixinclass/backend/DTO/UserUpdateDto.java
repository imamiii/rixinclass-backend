package com.rixinclass.backend.DTO;

import lombok.Data;

/**
 * 用户信息修改入参
 */
@Data
public class UserUpdateDto {
    private String username; // 用户名
    private String email; // 邮箱
    private String password; // 密码（可选，不修改则不传）
}