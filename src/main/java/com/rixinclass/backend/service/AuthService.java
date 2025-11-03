package com.rixinclass.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rixinclass.backend.DTO.LoginDto;
import com.rixinclass.backend.DTO.LoginRespDto;
import com.rixinclass.backend.entity.TSysLoginLog;
import com.rixinclass.backend.entity.TSysUser;
import com.rixinclass.backend.mapper.TSysLoginLogMapper;
import com.rixinclass.backend.mapper.TSysUserMapper;
import com.rixinclass.backend.utils.JwtUtil;
import com.rixinclass.backend.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Resource
    private TSysUserMapper sysUserMapper;

    @Resource
    private TSysLoginLogMapper loginLogMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtUtil jwtUtil;

    public Result<LoginRespDto> login(LoginDto loginDto) {
        // 1. 查询用户（按工号）
        QueryWrapper<TSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("work_no", loginDto.getWorkNo());
        TSysUser user = sysUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.error("工号不存在");
        }

        // 2. 验证密码
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }

        // 3. 生成JWT令牌
        String token = jwtUtil.generateToken(user.getUserId(), user.getRole());

        // 4. 记录登录日志
        TSysLoginLog log = new TSysLoginLog();
        log.setUserId(user.getUserId());
        log.setOperation("登录");
        log.setOperateTime(LocalDateTime.now());
        loginLogMapper.insert(log);

        // 5. 封装返回结果
        LoginRespDto resp = new LoginRespDto();
        resp.setToken(token);
        resp.setUsername(user.getUsername());
        resp.setRole(user.getRole());
        resp.setWorkNo(user.getWorkNo());
        return Result.success(resp);
    }

    public Result<String> logout(String token) {
        // 1. 解析令牌获取用户ID
        Long userId = jwtUtil.getUserIdFromToken(token.replace("Bearer ", ""));
        if (userId == null) {
            return Result.unAuth("令牌无效");
        }

        // 2. 记录登出日志
        TSysLoginLog log = new TSysLoginLog();
        log.setUserId(userId);
        log.setOperation("登出");
        log.setOperateTime(LocalDateTime.now());
        loginLogMapper.insert(log);

        return Result.success("登出成功");
    }
}