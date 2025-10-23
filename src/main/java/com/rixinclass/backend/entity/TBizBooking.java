package com.rixinclass.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Description 排课申请表
 * @Author DengWenyu
 * @Date 2025/10/23 19:09
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_biz_booking")
public class TBizBooking extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "booking_id", type = IdType.AUTO)
    private Long bookingId;

    /** 申请编号 */
    private String bookingNo;

    /** 申请教师 ID */
    private Long userId;

    /** 学年 */
    private String academicYear;

    /** 学期 */
    private String semester;

    /** 课程代码 */
    private String courseCode;

    /** 课程类型 */
    private String courseType;

    /** 课程名称 */
    private String courseName;

    /** 大纲要求实验学时 */
    private Integer requiredHours;

    /** 预约实验学时 */
    private Integer bookingHours;

    /** 授课班级 */
    private String className;

    /** 学生人数 */
    private Integer studentCount;

    /** 时间段（JSON 字符串） */
    private String timeSlots;

    /** 软件环境要求 */
    private String softwareRequirements;

    /** 其他要求/备注 */
    private String otherRequirements;

    /** 教师姓名 */
    private String teacherName;

    /** 教师电话 */
    private String teacherPhone;

    /** 教师邮箱 */
    private String teacherEmail;

    /** 教师签名图片 URL */
    private String teacherSignature;

    /** 状态：0 待审；1 通过；2 拒绝；3 取消 */
    private Integer status;

    /** 审核人 ID */
    private Long reviewUserId;

    /** 审核时间 */
    private LocalDateTime reviewTime;

    /** 审核备注/拒绝原因 */
    private String reviewRemark;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}
