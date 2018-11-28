package com.maplife.bo;

import lombok.Data;
import java.util.Date;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Data
public class OutputItemCommentBo {
    private Integer commentId;
    private UserBo user;
    private String content;
    private String imageUrl;
    private Date entryDatetime;
}
