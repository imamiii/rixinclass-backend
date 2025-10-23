-- =========================================================
-- RixinClass 实验室排课系统 - 初始化建库建表脚本
-- 版本: v1.0
-- 数据库: rixinclass
-- 字符集: utf8mb4 / utf8mb4_unicode_ci
-- =========================================================

/*!40101 SET NAMES utf8mb4 */;
CREATE DATABASE IF NOT EXISTS `rixinclass`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `rixinclass`;

-- =========================================================
-- 1. 用户表（教师/管理员）
-- =========================================================
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username`       VARCHAR(50)  NOT NULL                COMMENT '用户名',
  `password`       VARCHAR(100) NOT NULL                COMMENT '登录密码',
  `nickname`       VARCHAR(50)  DEFAULT NULL            COMMENT '昵称',
  `avatar`         VARCHAR(255) DEFAULT NULL            COMMENT '头像URL',
  `teacher_name`   VARCHAR(50)  DEFAULT NULL            COMMENT '教师真实姓名',
  `teacher_phone`  VARCHAR(20)  DEFAULT NULL            COMMENT '教师电话',
  `teacher_email`  VARCHAR(100) DEFAULT NULL            COMMENT '教师邮箱',
  `openid`         VARCHAR(100) DEFAULT NULL            COMMENT '微信OpenID（小程序登录可用）',
  `user_type`      TINYINT      NOT NULL DEFAULT 1      COMMENT '用户类型：1教师 2管理员',
  `status`         TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0禁用 1正常',
  `create_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`     TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_openid` (`openid`),
  KEY `idx_user_type_status` (`user_type`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表（教师/管理员）';


-- =========================================================
-- 2. 登录日志表（仅记录必要信息，注意隐私）
-- =========================================================
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log` (
  `log_id`       BIGINT       NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id`      BIGINT       DEFAULT NULL            COMMENT '用户ID（软关联）',
  `username`     VARCHAR(50)  DEFAULT NULL            COMMENT '用户名快照（便于排查）',
  `login_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `logout_time`  DATETIME     DEFAULT NULL            COMMENT '登出时间（可能为空）',
  `ip_addr`      VARCHAR(64)  DEFAULT NULL            COMMENT '登录IP',
  `ua`           VARCHAR(255) DEFAULT NULL            COMMENT '设备/浏览器UA',
  `result`       TINYINT      NOT NULL DEFAULT 1      COMMENT '结果：1成功 0失败',
  `fail_reason`  VARCHAR(255) DEFAULT NULL            COMMENT '失败原因（密码错误/锁定等）',
  PRIMARY KEY (`log_id`),
  KEY `idx_user_time` (`user_id`, `login_time`),
  KEY `idx_result_time` (`result`, `login_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录日志';


-- =========================================================
-- 3. 实验室表
-- =========================================================
DROP TABLE IF EXISTS `tb_laboratory`;
CREATE TABLE `tb_laboratory` (
  `lab_id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '实验室ID',
  `lab_code`       VARCHAR(50)  NOT NULL                COMMENT '实验室编号（如：505）',
  `lab_name`       VARCHAR(100) NOT NULL                COMMENT '实验室名称',
  `building`       VARCHAR(50)  NOT NULL                COMMENT '所在楼栋（如：软件楼）',
  `floor`          INT          DEFAULT NULL            COMMENT '楼层',
  `capacity`       INT          NOT NULL                COMMENT '容量（可容纳人数）',
  `software_env`   TEXT         DEFAULT NULL            COMMENT '软件环境（JSON）',
  `hardware_env`   TEXT         DEFAULT NULL            COMMENT '硬件环境/设备说明',
  `support_notes`  TEXT         DEFAULT NULL            COMMENT '支持课程/使用说明',
  `lab_admin`      VARCHAR(50)  DEFAULT NULL            COMMENT '实验室负责人',
  `status`         TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0维护 1正常 2停用',
  `remark`         TEXT         DEFAULT NULL            COMMENT '备注',
  `create_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`     TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`lab_id`),
  UNIQUE KEY `uk_lab_code` (`lab_code`),
  KEY `idx_status` (`status`),
  KEY `idx_building_floor` (`building`, `floor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='实验室信息';


-- =========================================================
-- 4. 排课申请表
-- =========================================================
DROP TABLE IF EXISTS `tb_booking`;
CREATE TABLE `tb_booking` (
  `booking_id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `booking_no`        VARCHAR(50)  NOT NULL                COMMENT '申请编号（唯一）',
  `user_id`           BIGINT       NOT NULL                COMMENT '申请教师ID（软关联）',
  `academic_year`     VARCHAR(20)  NOT NULL                COMMENT '学年（如：2025-2026）',
  `semester`          VARCHAR(20)  NOT NULL                COMMENT '学期（如：第一学期）',
  `course_code`       VARCHAR(50)  NOT NULL                COMMENT '课程代码',
  `course_type`       VARCHAR(30)  NOT NULL                COMMENT '课程类型（如：实验教学/作业/实习/毕设等）',
  `course_name`       VARCHAR(100) NOT NULL                COMMENT '课程名称',
  `required_hours`    INT          NOT NULL                COMMENT '大纲要求实验学时',
  `booking_hours`     INT          NOT NULL                COMMENT '预约实验学时',
  `class_name`        VARCHAR(100) NOT NULL                COMMENT '授课班级',
  `student_count`     INT          NOT NULL                COMMENT '学生人数',
  `time_slots`        JSON         DEFAULT NULL            COMMENT '时间段（JSON）',
  `software_requirements` TEXT     DEFAULT NULL            COMMENT '软件环境要求',
  `other_requirements`    TEXT     DEFAULT NULL            COMMENT '其他要求/备注',
  `teacher_name`      VARCHAR(50)  NOT NULL                COMMENT '教师姓名',
  `teacher_phone`     VARCHAR(20)  NOT NULL                COMMENT '教师电话',
  `teacher_email`     VARCHAR(100) NOT NULL                COMMENT '教师邮箱',
  `teacher_signature` VARCHAR(255) DEFAULT NULL            COMMENT '教师签名图片URL',
  `status`            TINYINT      NOT NULL DEFAULT 0      COMMENT '状态：0待审 1通过 2拒绝 3取消',
  `review_user_id`    BIGINT       DEFAULT NULL            COMMENT '审核人ID（管理员）',
  `review_time`       DATETIME     DEFAULT NULL            COMMENT '审核时间',
  `review_remark`     VARCHAR(255) DEFAULT NULL            COMMENT '审核备注/拒绝原因',
  `create_time`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`        TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `uk_booking_no` (`booking_no`),
  KEY `idx_user` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_academic_year` (`academic_year`, `semester`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排课申请表';


-- =========================================================
-- 5. 排课结果表（自动/人工），含冲突标识
-- =========================================================
DROP TABLE IF EXISTS `tb_schedule`;
CREATE TABLE `tb_schedule` (
  `schedule_id`    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '排课ID',
  `booking_id`     BIGINT       NOT NULL                COMMENT '关联申请ID（软关联）',
  `lab_id`         BIGINT       NOT NULL                COMMENT '实验室ID（软关联）',
  `weekday`        TINYINT      NOT NULL                COMMENT '星期（1-7）',
  `week_start`     INT          NOT NULL                COMMENT '开始周',
  `week_end`       INT          NOT NULL                COMMENT '结束周',
  `period_start`   INT          NOT NULL                COMMENT '开始节次',
  `period_end`     INT          NOT NULL                COMMENT '结束节次',
  `course_name`    VARCHAR(100) NOT NULL                COMMENT '课程名称（快照）',
  `teacher_name`   VARCHAR(50)  NOT NULL                COMMENT '教师姓名（快照）',
  `class_name`     VARCHAR(100) NOT NULL                COMMENT '班级名称（快照）',
  `student_count`  INT          NOT NULL                COMMENT '学生数（快照）',
  `lab_assistant`  VARCHAR(50)  DEFAULT NULL            COMMENT '实验员（可选）',
  `is_conflict`    TINYINT      NOT NULL DEFAULT 0      COMMENT '是否冲突：0否 1是',
  `conflict_reason`VARCHAR(255) DEFAULT NULL            COMMENT '冲突原因',
  `schedule_type`  TINYINT      NOT NULL DEFAULT 1      COMMENT '排课类型：1自动 2手动',
  `status`         TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0取消 1正常',
  `create_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`     TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`schedule_id`),
  KEY `idx_booking` (`booking_id`),
  KEY `idx_lab_time` (`lab_id`, `weekday`, `week_start`, `week_end`, `period_start`, `period_end`),
  KEY `idx_conflict` (`is_conflict`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='排课结果表';


-- =========================================================
-- 6. 公告/通知表（面向全体或指定对象）
-- =========================================================
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `notice_id`     BIGINT        NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `title`         VARCHAR(200)  NOT NULL                COMMENT '标题',
  `content`       TEXT          NOT NULL                COMMENT '正文',
  `notice_type`   VARCHAR(255)  NOT NULL                COMMENT '类型',
  `target_user_id`BIGINT        DEFAULT NULL            COMMENT '目标用户ID（NULL=全体）',
  `booking_id`    BIGINT        DEFAULT NULL            COMMENT '关联申请ID（可空）',
  `priority`      TINYINT       NOT NULL DEFAULT 0      COMMENT '优先级：0普通 1重要 2紧急',
  `is_read`       TINYINT       NOT NULL DEFAULT 0      COMMENT '已读：0否 1是（对单人通知可用）',
  `sender_id`     BIGINT        DEFAULT NULL            COMMENT '发送人（管理员）',
  `create_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`    TINYINT       NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`notice_id`),
  KEY `idx_target_read` (`target_user_id`, `is_read`),
  KEY `idx_booking` (`booking_id`),
  KEY `idx_priority_time` (`priority`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告/通知表';


-- =========================================================
-- 7. 通知/公告操作日志（发布、撤回、阅读等）
-- =========================================================
DROP TABLE IF EXISTS `tb_notice_log`;
CREATE TABLE `tb_notice_log` (
  `log_id`      BIGINT       NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `notice_id`   BIGINT       NOT NULL                COMMENT '通知ID（软关联）',
  `op_user_id`  BIGINT       DEFAULT NULL            COMMENT '操作人ID',
  `op_type`     VARCHAR(30)  NOT NULL                COMMENT '操作类型：publish/revoke/read/mark',
  `op_detail`   VARCHAR(255) DEFAULT NULL            COMMENT '操作描述',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`log_id`),
  KEY `idx_notice_time` (`notice_id`, `create_time`),
  KEY `idx_user_time` (`op_user_id`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知公告日志';


-- =========================================================
-- 8. 管理员审核日志（申请审核、冲突处理等）
-- =========================================================
DROP TABLE IF EXISTS `tb_admin_audit_log`;
CREATE TABLE `tb_admin_audit_log` (
  `audit_id`     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '审核日志ID',
  `booking_id`   BIGINT       DEFAULT NULL            COMMENT '关联申请ID',
  `schedule_id`  BIGINT       DEFAULT NULL            COMMENT '关联排课ID',
  `admin_user_id`BIGINT       NOT NULL                COMMENT '管理员ID',
  `action`       VARCHAR(50)  NOT NULL                COMMENT '动作：approve/reject/resolve_conflict/update_schedule/other',
  `remark`       VARCHAR(255) DEFAULT NULL            COMMENT '备注/理由',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`audit_id`),
  KEY `idx_booking` (`booking_id`),
  KEY `idx_schedule` (`schedule_id`),
  KEY `idx_admin_time` (`admin_user_id`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员审核日志';


-- =========================================================
-- 9. 数据统计表（聚合汇总结果，便于看板）
-- =========================================================
DROP TABLE IF EXISTS `tb_statistics`;
CREATE TABLE `tb_statistics` (
  `stat_id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `stat_date`          DATE         NOT NULL                COMMENT '统计日期',
  `stat_type`          VARCHAR(50)  NOT NULL                COMMENT '统计类型（daily/weekly/monthly/semester）',
  `academic_year`      VARCHAR(20)  DEFAULT NULL            COMMENT '学年',
  `semester`           VARCHAR(20)  DEFAULT NULL            COMMENT '学期',
  `total_bookings`     INT          NOT NULL DEFAULT 0      COMMENT '总申请数',
  `pending_bookings`   INT          NOT NULL DEFAULT 0      COMMENT '待审核数',
  `approved_bookings`  INT          NOT NULL DEFAULT 0      COMMENT '已通过数',
  `rejected_bookings`  INT          NOT NULL DEFAULT 0      COMMENT '已拒绝数',
  `total_schedules`    INT          NOT NULL DEFAULT 0      COMMENT '总排课数',
  `conflict_schedules` INT          NOT NULL DEFAULT 0      COMMENT '冲突排课数',
  `total_students`     INT          NOT NULL DEFAULT 0      COMMENT '总学生数',
  `lab_usage_rate`     DECIMAL(5,2) NOT NULL DEFAULT 0.00   COMMENT '实验室使用率(%)',
  `stat_data`          JSON         DEFAULT NULL            COMMENT '扩展统计JSON',
  `create_time`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`stat_id`),
  UNIQUE KEY `uk_date_type` (`stat_date`, `stat_type`),
  KEY `idx_year_sem` (`academic_year`, `semester`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计数据表';


-- =========================================================
-- 10. 系统配置表（当前学期、统一收集时间等）
-- =========================================================
DROP TABLE IF EXISTS `tb_system_config`;
CREATE TABLE `tb_system_config` (
  `config_id`    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `config_key`   VARCHAR(100) NOT NULL                COMMENT '配置键（唯一）',
  `config_value` TEXT         DEFAULT NULL            COMMENT '配置值',
  `config_type`  VARCHAR(50)  NOT NULL DEFAULT 'system' COMMENT '配置类型',
  `description`  VARCHAR(255) DEFAULT NULL            COMMENT '说明',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置表（键值对）';


-- =========================================================
-- 11. 黑名单日历（放假/调休/考试周/故障等禁排信息）
-- =========================================================
/*DROP TABLE IF EXISTS `tb_blackout_calendar`;
CREATE TABLE `tb_blackout_calendar` (
  `blk_id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `scope_type`    TINYINT      NOT NULL                COMMENT '范围：1全校 2实验室 3课程 4班级',
  `lab_id`        BIGINT       DEFAULT NULL            COMMENT '实验室ID（当 scope=2 时使用）',
  `title`         VARCHAR(100) NOT NULL                COMMENT '标题（如：国庆放假/设备维护）',
  `reason`        VARCHAR(255) DEFAULT NULL            COMMENT '原因说明',
  `start_time`    DATETIME     NOT NULL                COMMENT '开始时间',
  `end_time`      DATETIME     NOT NULL                COMMENT '结束时间',
  `repeat_rule`   VARCHAR(50)  DEFAULT NULL            COMMENT '重复规则（如：NONE/WEEKLY/MONTHLY）',
  `status`        TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0停用 1启用',
  `create_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`    TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`blk_id`),
  KEY `idx_scope_time` (`scope_type`, `start_time`, `end_time`),
  KEY `idx_lab_time` (`lab_id`, `start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='黑名单日历（放假/故障/考试周等禁排时间段）';*/


-- =========================================================
-- 12. 帮助中心（使用指南/FAQ/问题反馈 设置）
-- =========================================================
DROP TABLE IF EXISTS `tb_help_article`;
CREATE TABLE `tb_help_article` (
  `article_id`   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_type` TINYINT      NOT NULL                COMMENT '类型：0使用指南 1常见问题 2问题反馈',
  `title`        VARCHAR(200) NOT NULL                COMMENT '标题',
  `content`      MEDIUMTEXT   NOT NULL                COMMENT '内容（富文本/HTML）',
  `status`       TINYINT      NOT NULL DEFAULT 1      COMMENT '状态：0下线 1上线',
  `create_user`  BIGINT       DEFAULT NULL            COMMENT '创建人',
  `update_user`  BIGINT       DEFAULT NULL            COMMENT '更新人',
  `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`   TINYINT      NOT NULL DEFAULT 0      COMMENT '是否删除：0否 1是',
  PRIMARY KEY (`article_id`),
  KEY `idx_type_status` (`article_type`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='帮助中心（使用指南/FAQ/反馈）';


-- =========================================================
-- 13. 意见反馈表（教师端“意见反馈”）
-- =========================================================

-- =========================================================
-- 14. 审核记录显示（管理员+操作）已由 tb_admin_audit_log 覆盖
-- =========================================================


-- =========================================================
-- 15. 初始化基础配置与示例数据（可按需启用）
--   注意：生产环境请改为加密密码；此处明文仅示例！
-- =========================================================

-- 默认管理员（请在应用启动时用BCrypt更新密码）
INSERT INTO `tb_user` (`username`, `password`, `nickname`, `user_type`, `status`)
VALUES ('admin', 'admin123', '系统管理员', 2, 1);

-- 系统配置（当前学年学期、最大周数、每天节次、统一收集截止天数等）
INSERT INTO `tb_system_config` (`config_key`, `config_value`, `config_type`, `description`) VALUES
('current_academic_year', '2025-2026', 'system', '当前学年'),
('current_semester', '第一学期', 'system', '当前学期'),
('max_weeks', '20', 'system', '最大教学周数'),
('periods_per_day', '12', 'system', '每天节次数'),
('booking_deadline_days', '7', 'system', '统一收集：提交需提前天数');

-- 示例实验室
INSERT INTO `tb_laboratory` (`lab_code`, `lab_name`, `building`, `floor`, `capacity`, `software_env`, `hardware_env`, `status`)
VALUES
('A505', '软件开发实验室', '软件楼', 5, 60,
 JSON_OBJECT('os','Windows 10','software', JSON_ARRAY(JSON_OBJECT('name','IDEA','version','2023'), JSON_OBJECT('name','Eclipse','version','2021'))),
 '联想 台式机 i5/8G/256G', 1),
('A518', '图形图像实验室', '软件楼', 5, 50,
 JSON_OBJECT('os','Windows 10','software', JSON_ARRAY(JSON_OBJECT('name','Photoshop','version','2020'), JSON_OBJECT('name','3ds Max','version','2021'))),
 '戴尔 台式机 i7/16G/512G + 独显', 1);

-- 示例公告（面向全体）
INSERT INTO `tb_notice` (`title`, `content`, `notice_type`, `priority`)
VALUES ('2025-2026第一学期排课通知', '各位老师：请于截止日前提交排课申请；统一收集结束后将自动排课并进入管理员审核。', 1, 1);

-- 示例黑名单日历（全校放假）
/*INSERT INTO `tb_blackout_calendar` (`scope_type`, `title`, `reason`, `start_time`, `end_time`, `repeat_rule`, `status`)
VALUES (1, '国庆放假', '法定节假日', '2025-10-01 00:00:00', '2025-10-07 23:59:59', 'NONE', 1);*/

-- 示例教师账号（请用程序初始化时加密密码）
INSERT INTO `tb_user` (`username`, `password`, `nickname`, `teacher_name`, `teacher_phone`, `teacher_email`, `user_type`, `status`)
VALUES ('teacher001', 'teacher123', '张老师', '张三', '13800138001', 'zhangsan@example.com', 1, 1);

-- 示例申请（time_slots 用 JSON 表示）
INSERT INTO `tb_booking` (
  `booking_no`, `user_id`, `academic_year`, `semester`,
  `course_code`, `course_type`, `course_name`,
  `required_hours`, `booking_hours`, `class_name`, `student_count`,
  `time_slots`, `software_requirements`, `other_requirements`,
  `teacher_name`, `teacher_phone`, `teacher_email`, `status`
) VALUES (
  'BK2025010001',  (SELECT user_id FROM tb_user WHERE username='teacher001' LIMIT 1),
  '2025-2026', '第一学期',
  'CS101', '实验教学', 'Java程序设计',
  32, 32, '软件2301', 45,
  JSON_ARRAY(JSON_OBJECT('weekday',1,'weekStart',1,'weekEnd',16,'periodStart',3,'periodEnd',4)),
  'JDK 1.8, Eclipse', '需要联网',
  '张三', '13800138001', 'zhangsan@example.com', 0
);