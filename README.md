com.rixinclass.backend
├── RixinClassApplication.java       # 项目启动类
├── config                          # 配置类
│   ├── MyBatisPlusConfig.java      # MyBatis-Plus配置
│   ├── PasswordEncoderConfig.java  # 密码加密配置
│   └── WebMvcConfig.java           # 跨域配置
├── controller                      # 控制器（按业务功能）
│   ├── AuthController.java         # 登录登出
│   ├── UserInfoController.java     # 用户信息管理
│   ├── CourseHistoryController.java # 查看历史记录
│   ├── NoticeController.java       # 查看通知
│   ├── AnnouncementController.java # 查看公告
│   ├── HelpController.java         # 帮助中心查询（指南/FAQ/反馈）
│   ├── CourseAuditController.java  # 排课申请审核
│   ├── ScheduleController.java     # 课表查看（导出）
│   ├── LabController.java          # 实验室管理
│   ├── SystemConfigController.java # 系统配置
│   ├── AnnouncementManageController.java # 公告管理
│   ├── HelpManageController.java   # 帮助中心管理
│   ├── AuditRecordController.java  # 审核记录显示
│   └── DataStatController.java     # 数据统计
├── service                         # 服务层（按业务功能）
│   ├── AuthService.java            # 登录登出服务
│   ├── UserInfoService.java        # 用户信息管理服务
│   ├── CourseHistoryService.java   # 历史记录服务
│   ├── NoticeService.java          # 通知服务
│   ├── AnnouncementService.java    # 公告查询服务
│   ├── HelpService.java            # 帮助中心查询服务
│   ├── CourseAuditService.java     # 排课审核服务
│   ├── ScheduleService.java        # 课表服务
│   ├── LabService.java             # 实验室管理服务
│   ├── SystemConfigService.java    # 系统配置服务
│   ├── AnnouncementManageService.java # 公告管理服务
│   ├── HelpManageService.java      # 帮助中心管理服务
│   ├── AuditRecordService.java     # 审核记录服务
│   └── DataStatService.java        # 数据统计服务
├── mapper                          # 数据访问层
│   ├── TSysUserMapper.java         # 用户Mapper
│   ├── UserLoginLogMapper.java     # 登录日志Mapper
│   ├── TBizBookingMapper.java      # 排课申请Mapper
│   ├── TBizScheduleMapper.java     # 排课结果Mapper
│   ├── NoticeMapper.java           # 通知Mapper
│   ├── AnnouncementMapper.java     # 公告Mapper
│   ├── HelpFeedbackMapper.java     # 帮助反馈Mapper
│   ├── TLabMapper.java             # 实验室Mapper
│   ├── SystemConfigMapper.java     # 系统配置Mapper
│   ├── AdminAuditLogMapper.java    # 审核日志Mapper
│   └── SysDataStatMapper.java      # 数据统计Mapper
├── entity                          # 实体类（对应数据库表）
│   ├── TSysUser.java               # 用户实体
│   ├── UserLoginLog.java           # 登录日志实体
│   ├── TBizBooking.java            # 排课申请实体
│   ├── TBizSchedule.java           # 排课结果实体
│   ├── Notice.java                 # 通知实体
│   ├── Announcement.java           # 公告实体
│   ├── HelpFeedback.java           # 帮助反馈实体
│   ├── TLab.java                   # 实验室实体
│   ├── SystemConfig.java           # 系统配置实体
│   ├── AdminAuditLog.java          # 审核日志实体
│   └── SysDataStat.java            # 数据统计实体
├── dto                             # 数据传输对象（入参/出参）
│   ├── LoginDto.java               # 登录入参
│   ├── LoginRespDto.java           # 登录出参
│   ├── UserUpdateDto.java          # 用户信息修改入参
│   ├── CourseApplyDto.java         # 排课申请入参
│   ├── AuditDto.java               # 审核入参
│   ├── LabDto.java                 # 实验室管理入参
│   ├── SystemConfigDto.java        # 系统配置入参
│   ├── AnnouncementDto.java        # 公告发布入参
│   └── HelpUpdateDto.java          # 帮助中心更新入参
└── util                            # 工具类
    ├── Result.java                 # 统一返回结果
    ├── BusinessConstant.java       # 业务常量
    └── JwtUtil.java                # JWT工具类
