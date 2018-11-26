package com.maplife.entity;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Data
public class ItemCategory {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer categoryId;
    private String name;
    private String description;
    private Character active;
    private Integer entryId;
    private Date entryDatetime;
    private Integer deleteId;
    private Date deleteDatetime;
}
