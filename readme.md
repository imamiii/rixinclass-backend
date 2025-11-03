## RixinClassApplication.java（项目启动类）
## config（配置类）
1. MyBatisPlusConfig.java # MyBatis-Plus配置
2. PasswordEncoderConfig.java # 密码加密配置
3. WebMvcConfig.java # 跨域配置
## controller（控制器，按业务功能）
1. AuthController.java # 登录登出
2. UserInfoController.java # 用户信息管理
3. CourseHistoryController.java # 查看历史记录
4. NoticeController.java # 查看通知
5. AnnouncementController.java # 查看公告
6. HelpController.java # 帮助中心查询（指南/FAQ/反馈）
7. CourseAuditController.java # 排课申请审核
8. ScheduleController.java # 课表查看（导出）
9. LabController.java # 实验室管理
10. SystemConfigController.java # 系统配置
11. AnnouncementManageController.java # 公告管理
12. HelpManageController.java # 帮助中心管理
13. AuditRecordController.java # 审核记录显示
14. DataStatController.java # 数据统计
## service（服务层，按业务功能）
1. AuthService.java # 登录登出服务
2. UserInfoService.java # 用户信息管理服务
3. CourseHistoryService.java # 历史记录服务
4. NoticeService.java # 通知服务
5. AnnouncementService.java # 公告查询服务
6. HelpService.java # 帮助中心查询服务
7. CourseAuditService.java # 排课审核服务
8. ScheduleService.java # 课表服务
9. LabService.java # 实验室管理服务
10. SystemConfigService.java # 系统配置服务
11. AnnouncementManageService.java # 公告管理服务
12. HelpManageService.java # 帮助中心管理服务
13. AuditRecordService.java # 审核记录服务
14. DataStatService.java # 数据统计服务
## mapper（数据访问层）
1. TSysUserMapper.java # 用户Mapper
2. UserLoginLogMapper.java # 登录日志Mapper
3. TBizBookingMapper.java # 排课申请Mapper
4. TBizScheduleMapper.java # 排课结果Mapper
5. NoticeMapper.java # 通知Mapper
6. AnnouncementMapper.java # 公告Mapper
7. HelpFeedbackMapper.java # 帮助反馈Mapper
8. TLabMapper.java # 实验室Mapper
9. SystemConfigMapper.java # 系统配置Mapper
10. AdminAuditLogMapper.java # 审核日志Mapper
11. SysDataStatMapper.java # 数据统计Mapper
## entity（实体类，对应数据库表）
1. TSysUser.java # 用户实体
2. UserLoginLog.java # 登录日志实体
3. TBizBooking.java # 排课申请实体
4. TBizSchedule.java # 排课结果实体
5. Notice.java # 通知实体
6. Announcement.java # 公告实体
7. HelpFeedback.java # 帮助反馈实体
8. TLab.java # 实验室实体
9. SystemConfig.java # 系统配置实体
10. AdminAuditLog.java # 审核日志实体
11. SysDataStat.java # 数据统计实体
## dto（数据传输对象，入参/出参）
1. LoginDto.java # 登录入参
2. LoginRespDto.java # 登录出参
3. UserUpdateDto.java # 用户信息修改入参
4. CourseApplyDto.java # 排课申请入参
5. AuditDto.java # 审核入参
6. LabDto.java # 实验室管理入参
7. SystemConfigDto.java # 系统配置入参
8. AnnouncementDto.java # 公告发布入参
9. HelpUpdateDto.java # 帮助中心更新入参
## util（工具类）
1. Result.java # 统一返回结果
2. BusinessConstant.java # 业务常量
3. JwtUtil.java # JWT工具类