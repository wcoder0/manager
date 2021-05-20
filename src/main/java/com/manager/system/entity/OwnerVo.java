package com.manager.system.entity;


import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OwnerVo implements Serializable {

   private static final long serialVersionUID=1L;


   private String idCard;


   private String name;


   private String sex;


   private Integer age;


   private String address;


   private Date createTime;


   private Date updateTime;
}
