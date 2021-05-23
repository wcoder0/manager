package com.manager.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Blob;
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
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("owner")
public class Owner implements Serializable {

   private static final long serialVersionUID = 1L;

   @TableId(value = "id_card", type = IdType.INPUT)
   private String idCard;

   @TableField("name")
   private String name;

   @TableField("sex")
   private String sex;

   @TableField("age")
   private Integer age;

   @TableField("address")
   private String address;

   @TableField("picture")
   private byte[] picture;

   @TableField("create_time")
   private Date createTime;

   @TableField("update_time")
   private Date updateTime;

   @TableField("path")
   private String path;

   @TableField("image")
   private String image;

   @TableField("password")
   private String password;
}
