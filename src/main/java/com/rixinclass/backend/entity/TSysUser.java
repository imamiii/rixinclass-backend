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
 * @Description 系统用户表
 * @Author DengWenyu
 * @Date 2025/10/23 19:04
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_sys_user")
public class TSysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /** 用户名 */
    private String username;

    /** 登录密码 */
    private String password;

    /** 昵称 */
    private String nickname;

    /** 头像 URL */
    private String avatar;

    /** 教师真实姓名 */
    private String teacherName;

    /** 教师电话 */
    private String teacherPhone;

    /** 教师邮箱 */
    private String teacherEmail;

    /** 微信 OpenID（小程序登录） */
    private String openid;

    /** 用户类型：0 教师；1 管理员 */
    private Integer userType;

    /** 状态：0 禁用；1 正常 */
    private Integer status;

    /** 是否删除：0 否；1 是 */
    private Integer isDeleted;
}
