package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Data
public class Item {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer itemId;
    private Integer userId;
    private Integer categoryId;
    private String title;
    private Date cuteOffDate;
    private Integer status;
    private Integer score;
    private String locationName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer viewCount;
    private Integer entryId;
    private Date entryDatetime;
    private Integer updateId;
    private Date updateDatetime;
    private Integer deleteId;
    private Integer deleteDatetime;
}
