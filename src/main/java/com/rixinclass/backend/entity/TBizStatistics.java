package com.rixinclass.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description 统计数据表
 * @Author DengWenyu
 * @Date 2025/10/23 19:08
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_biz_statistics")
public class TBizStatistics extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "stat_id", type = IdType.AUTO)
    private Long statId;

    /** 统计日期 */
    private LocalDate statDate;

    /** 统计类型：daily/weekly/monthly/semester */
    private String statType;

    /** 学年 */
    private String academicYear;

    /** 学期 */
    private String semester;

    /** 总申请数 */
    private Integer totalBookings;

    /** 待审核数 */
    private Integer pendingBookings;

    /** 已通过数 */
    private Integer approvedBookings;

    /** 已拒绝数 */
    private Integer rejectedBookings;

    /** 总排课数 */
    private Integer totalSchedules;

    /** 冲突排课数 */
    private Integer conflictSchedules;

    /** 总学生数 */
    private Integer totalStudents;

    /** 实验室使用率(%) */
    private BigDecimal labUsageRate;

    /** 扩展统计 JSON */
    private String statData;

}
