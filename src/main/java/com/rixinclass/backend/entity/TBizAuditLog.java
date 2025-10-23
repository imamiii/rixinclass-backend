package com.rixinclass.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @Description 管理员审核记录表
 * @Author DengWenyu
 * @Date 2025/10/23 19:10
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_biz_audit_log")
public class TBizAuditLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "audit_id", type = IdType.AUTO)
    private Long auditId;

    /** 关联申请 ID */
    private Long bookingId;

    /** 关联排课 ID */
    private Long scheduleId;

    /** 管理员 ID */
    private Long adminUserId;

    /** 动作：approve/reject/resolve_conflict/update_schedule/other */
    private String action;

    /** 备注/理由 */
    private String remark;

}
