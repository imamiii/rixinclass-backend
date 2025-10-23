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
 * @Description 通知/公告记录表
 * @Author DengWenyu
 * @Date 2025/10/23 19:41
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_cms_notice_record")
public class TCmsNoticeRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /** 通知 ID */
    private Long noticeId;

    /** 操作人 ID */
    private Long opUserId;

    /** 操作类型：publish/revoke/read/mark */
    private String opType;

    /** 操作描述 */
    private String opDetail;

}
