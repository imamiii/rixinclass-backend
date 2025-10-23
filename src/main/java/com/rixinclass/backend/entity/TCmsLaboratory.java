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
 * @Description 实验室信息表
 * @Author DengWenyu
 * @Date 2025/10/23 19:06
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_cms_laboratory")
public class TCmsLaboratory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "lab_id", type = IdType.AUTO)
    private Long labId;

    /** 实验室编号 */
    private String labCode;

    /** 实验室名称 */
    private String labName;

    /** 所在楼栋 */
    private String building;

    /** 楼层 */
    private Integer floor;

    /** 可容纳人数 */
    private Integer capacity;

    /** 软件环境（JSON 字符串） */
    private String softwareEnv;

    /** 硬件环境或设备说明 */
    private String hardwareEnv;

    /** 支持课程/使用说明 */
    private String supportNotes;

    /** 实验室负责人 */
    private String labAdmin;

    /** 状态：0 维护；1 正常；2 停用 */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}