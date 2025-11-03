package com.rixinclass.backend.utils;

/**
 * 业务常量定义
 */
public class BusinessConstant {
    // 用户角色
    public static final String ROLE_TEACHER = "teacher"; // 教师
    public static final String ROLE_ADMIN = "admin";     // 管理员

    // 排课申请状态
    public static final String APPLY_STATUS_PENDING = "pending";   // 待审核
    public static final String APPLY_STATUS_APPROVED = "approved"; // 已通过
    public static final String APPLY_STATUS_REJECTED = "rejected"; // 未通过

    // 通知状态
    public static final String NOTICE_STATUS_ALL = "all";
    public static final String NOTICE_STATUS_PENDING = "pending";
    public static final String NOTICE_STATUS_APPROVED = "approved";
    public static final String NOTICE_STATUS_REJECTED = "rejected";
}