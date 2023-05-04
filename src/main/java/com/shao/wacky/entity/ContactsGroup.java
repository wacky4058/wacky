package com.shao.wacky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 联系人组
 */
@Data
public class ContactsGroup {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;

    @TableField("contacts")
    private List<Long> contacts;

    @TableField("createTime")
    private Timestamp createTime;
    @TableField("updateTime")
    private Timestamp updateTime;
    @TableField("createBy")
    private Long createBy;
    @TableField("updateBy")
    private Long updateBy;

    @TableField("owner")
    private Long owner;

    @TableField("delete_flag")
    private int deleteFlag;
}
