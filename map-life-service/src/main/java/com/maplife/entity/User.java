package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer userId;
    @TableField(value = "wx_openid")
    private String wxOpenId;
    private String phone;
    private String nickName;
    private String avatarUrl;
    private Integer entryId;
    private Date entryDatetime;
}
