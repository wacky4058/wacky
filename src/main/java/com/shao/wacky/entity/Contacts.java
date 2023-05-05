package com.shao.wacky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 联系人
 */
@Data
public class Contacts implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private int sex;

    @TableField("address")
    private String address;

    @TableField("remark")
    private String remark;

    @TableField("create_time")
    private Timestamp createTime;
    @TableField("update_time")
    private Timestamp updateTime;
    @TableField("create_by")
    private Long createBy;
    @TableField("update_by")
    private Long updateBy;
    @TableField("owner")
    private Long owner;

    @TableField("delete_flag")
    private int deleteFlag;
}
