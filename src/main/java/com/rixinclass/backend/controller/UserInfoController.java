package com.rixinclass.backend.controller;

import com.rixinclass.backend.DTO.UserUpdateDto;
import com.rixinclass.backend.entity.TSysUser;
import com.rixinclass.backend.security.JwtUtil;
import com.rixinclass.backend.service.UserInfoService;
import com.rixinclass.backend.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户信息", description = "用户信息查询与修改接口")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "获取当前登录用户的基本信息")
    public Result<TSysUser> getUserInfo(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.getUserId(token.replace("Bearer ", ""));
        return userInfoService.getUserInfo(userId);
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    @Operation(summary = "更新用户信息", description = "修改邮箱或密码")
    public Result<String> updateUserInfo(
            @RequestHeader("Authorization") String token,
            @RequestBody UserUpdateDto dto) {
        Long userId = jwtUtil.getUserId(token.replace("Bearer ", ""));
        return userInfoService.updateUserInfo(userId, dto);
    }
}