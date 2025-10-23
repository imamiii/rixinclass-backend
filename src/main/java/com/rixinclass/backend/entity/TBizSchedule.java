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
 * @Description 排课结果表
 * @Author DengWenyu
 * @Date 2025/10/23 19:09
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_biz_schedule")
public class TBizSchedule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "schedule_id", type = IdType.AUTO)
    private Long scheduleId;

    /** 关联申请 ID */
    private Long bookingId;

    /** 实验室 ID */
    private Long labId;

    /** 星期（1-7） */
    private Integer weekday;

    /** 开始周 */
    private Integer weekStart;

    /** 结束周 */
    private Integer weekEnd;

    /** 开始节次 */
    private Integer periodStart;

    /** 结束节次 */
    private Integer periodEnd;

    /** 课程名称快照 */
    private String courseName;

    /** 教师姓名快照 */
    private String teacherName;

    /** 班级名称快照 */
    private String className;

    /** 学生数快照 */
    private Integer studentCount;

    /** 实验员（可选） */
    private String labAssistant;

    /** 是否冲突：0 否；1 是 */
    private Integer isConflict;

    /** 冲突原因 */
    private String conflictReason;

    /** 排课类型：0 自动；1 手动 */
    private Integer scheduleType;

    /** 状态：0 取消；1 正常 */
    private Integer status;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}
