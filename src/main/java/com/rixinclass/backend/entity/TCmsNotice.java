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
 * @Description 公告/通知表
 * @Author DengWenyu
 * @Date 2025/10/23 19:05
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_cms_notice")
public class TCmsNotice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /** 标题 */
    private String title;

    /** 正文内容 */
    private String content;

    /** 通知类型 */
    private String noticeType;

    /** 目标用户 ID（为空代表全体） */
    private Long targetUserId;

    /** 关联申请 ID（可空） */
    private Long bookingId;

    /** 优先级：0 普通；1 重要；2 紧急 */
    private Integer priority;

    /** 是否已读（单人通知可用）：0 否；1 是 */
    private Integer isRead;

    /** 发送人（管理员） */
    private Long senderId;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}
