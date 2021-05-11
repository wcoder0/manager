package com.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hannibal
 * @since 2021-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;

   @TableId(value = "id", type = IdType.AUTO)
   private Integer id;

   @TableField("name")
   private String name;

   @TableField("email")
   private String email;

   @TableField("username")
   private String username;

   @TableField("age")
   private Integer age;

   @TableField("sex")
   private String sex;

   @TableField("address")
   private String address;

   @TableField("phone")
   private String phone;

   @TableField("password")
   private String password;

   @TableField("create_time")
   private Date createTime;

   @TableField("update_time")
   private Date updateTime;

   @TableField("type")
   private String type;

   @TableField("status")
   private Integer status;
}
