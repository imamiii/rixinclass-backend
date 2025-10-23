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
 * @Description 帮助中心（使用指南/FAQ/反馈）
 * @Author DengWenyu
 * @Date 2025/10/23 19:07
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_cms_help")
public class TCmsHelp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /** 类型：0 使用指南；1 常见问题；2 问题反馈 */
    private Integer articleType;

    /** 标题 */
    private String title;

    /** 内容（富文本/HTML） */
    private String content;

    /** 状态：0 下线；1 上线 */
    private Integer status;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}
