package com.rixinclass.backend.DTO;

import lombok.Data;

/**
 * 登录接口出参
 */
@Data
public class LoginRespDto {
    private String token; // 身份令牌
    private String username; // 用户名
    private String role; // 角色（teacher/admin）
    private String workNo; // 工号
}