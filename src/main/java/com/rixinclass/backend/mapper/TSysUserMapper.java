package com.rixinclass.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rixinclass.backend.entity.TSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TSysUserMapper extends BaseMapper<TSysUser> {
    // 按工号查询用户（登录用）
    @Select("SELECT * FROM t_sys_user WHERE work_no = #{workNo}")
    TSysUser selectByWorkNo(String workNo);

    // 自定义分页查询（示例：按角色查询用户）
    IPage<TSysUser> selectUserByRole(Page<TSysUser> page, @Param("roleId") Long roleId);

    // 复杂条件查询（使用MyBatis-Plus条件构造器）
    IPage<TSysUser> selectUserPage(Page<TSysUser> page, @Param(Constants.WRAPPER) Wrapper<TSysUser> queryWrapper);
}