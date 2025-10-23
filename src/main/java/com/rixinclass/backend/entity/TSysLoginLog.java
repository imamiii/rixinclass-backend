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
 * @Description 用户登录日志
 * @Author DengWenyu
 * @Date 2025/10/23 19:05
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_sys_login_log")
public class TSysLoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /** 用户 ID */
    private Long userId;

    /** 用户名快照 */
    private String username;

    /** 登录时间 */
    private LocalDateTime loginTime;

    /** 登出时间 */
    private LocalDateTime logoutTime;

    /** 登录 IP 地址 */
    private String ipAddr;

    /** 浏览器/设备 UA */
    private String ua;

    /** 登录结果：1 成功；0 失败 */
    private Integer result;

    /** 失败原因 */
    private String failReason;
}
