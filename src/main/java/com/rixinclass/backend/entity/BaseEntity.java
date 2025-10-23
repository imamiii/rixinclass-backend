package com.rixinclass.backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *@Description 基础实体类
 *@Author
 *@Date
 *@Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 创建人
     */
    Long createUser;

    /**
     * 更新时间
     */
    Date updateTime;

    /**
     * 更新者
     */
    Long updateUser;
}
