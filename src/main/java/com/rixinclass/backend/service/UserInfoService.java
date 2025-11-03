package com.rixinclass.backend.service;

import com.rixinclass.backend.DTO.UserUpdateDto;
import com.rixinclass.backend.entity.TSysUser;
import com.rixinclass.backend.mapper.TSysUserMapper;
import com.rixinclass.backend.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Resource
    private TSysUserMapper sysUserMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户ID查询用户信息
     */
    public Result<TSysUser> getUserInfo(Long userId) {
        TSysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        // 隐藏密码
        user.setPassword(null);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     */
    public Result<String> updateUserInfo(Long userId, UserUpdateDto dto) {
        TSysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        // 更新邮箱
        if (dto.getEmail() != null) {
            user.setTeacherEmail(dto.getEmail());
        }
        // 更新密码（如果传入）
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        sysUserMapper.updateById(user);
        return Result.success("更新成功");
    }
}