package com.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@TableName("epidemic_report")
public class EpidemicReport implements Serializable {

    private static final long serialVersionUID=1L;

   /* 姓名 年龄 电话 家庭地址 今日体温 是否发烧 是否咳嗽 其他症状 当前所在地 是否外出 外出地点*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("sex")
    private String sex;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("temperature")
    private Float temperature;

    @TableField("status")
    private Integer status;

    @TableField("report_time")
    private Date reportTime;

    @TableField("fever")
    private String fever;

    @TableField("cough")
    private String cough;

    @TableField("symptoms")
    private String symptoms;

    @TableField("out_side")
    private String outSide;

    @TableField("out_addr")
    private String outAddr;

    @TableField("curr_addr")
    private String currAddr;

}
