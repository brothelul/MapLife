package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Data
public class ItemLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer likeId;
    private Integer userId;
    private Integer likeType;
    private Integer targetId;
    private Integer entryId;
    private Date entryDatetime;
    private Integer deleteId;
    private Date deleteDatetime;
}
