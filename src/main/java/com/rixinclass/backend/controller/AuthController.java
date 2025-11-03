package com.rixinclass.backend.controller;

import com.rixinclass.backend.DTO.LoginDto;
import com.rixinclass.backend.service.AuthService;
import com.rixinclass.backend.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 登录登出控制器
 * 对应需求：登录登出（教师/管理员通用）
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "登录登出", description = "用户身份验证相关接口")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * 用户登录（教师/管理员通过工号密码登录）
     * 流程：验证身份 → 生成JWT令牌 → 记录登录日志
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "输入工号和密码，返回身份令牌")
    public Result login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    /**
     * 用户登出（失效当前令牌，记录登出日志）
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "清除登录状态，记录登出日志")
    public Result logout(@RequestHeader("Authorization") String token) {
        return authService.logout(token);
    }
}