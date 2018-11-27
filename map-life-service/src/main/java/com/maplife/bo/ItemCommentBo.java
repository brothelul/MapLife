package com.maplife.bo;

import lombok.Data;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Data
public class ItemCommentBo {
    private Integer itemId;
    private Integer parentId;
    private Integer userId;
    private String content;
    private String imageUrl;
}
