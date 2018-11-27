package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Data
public class ItemComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer commentId;
    private Integer itemId;
    private Integer userId;
    private Integer parentId;
    private String content;
    private String imageUrl;
    private Integer entryId;
    private Date entryDatetime;
    private Integer deleteId;
    private Date deleteDatetime;
}
