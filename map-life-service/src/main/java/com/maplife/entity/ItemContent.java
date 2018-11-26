package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Data
public class ItemContent {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer contentId;
    private Integer itemId;
    private Integer seqNo;
    private Integer contentType;
    private String content;
    private Integer entryId;
    private Date entryDatetime;
    private Integer deleteId;
    private Date deleteDatetime;
}
