package com.rixinclass.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录日志实体类
 */
@Data
@TableName("t_sys_login_log") // 对应数据库表名
public class TSysLoginLog {
    @TableId(type = IdType.AUTO)
    private Long id; // 日志ID
    private Long userId; // 用户ID
    private String operation; // 操作类型（登录/登出）
    private LocalDateTime operateTime; // 操作时间（新增此字段）
    private String loginIp; // 登录IP

    // 添加 setOperateTime 方法（解决报错）
    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    // Lombok的@Data会自动生成getter/setter，但若未生效，可手动补充
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }
}