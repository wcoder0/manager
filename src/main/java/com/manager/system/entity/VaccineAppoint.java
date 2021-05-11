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
@TableName("vaccine_appoint")
public class VaccineAppoint implements Serializable {

    private static final long serialVersionUID=1L;

    /*姓名 年龄 电话 是否发热 是否过敏 是否高血压 是否有慢性疾病 是否符合接种条件 备注*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("age")
    private Integer age;

    @TableField("idcard")
    private String idcard;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("hospital")
    private String hospital;

    @TableField("appoint_time")
    private Date appointTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("fever")
    private String fever;

    @TableField("allergy")
    private String allergy;

    @TableField("hypertension")
    private String hypertension;

    @TableField("chronic_disease")
    private String chronicDisease;

    @TableField("meet_condition")
    private String meetCondition;

    @TableField("remark")
    private String remark;

}
