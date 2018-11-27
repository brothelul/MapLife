package com.maplife.bo;

import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Data
public class OutputItemBo extends ItemBo {
    private Integer itemId;
    private ItemCategoryBo itemCategory;
    private UserBo author;
    private Date entryDatetime;
    private Integer viewCount;
    private Integer status;
}
