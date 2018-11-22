package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName(value = "user")
@Getter
@Setter
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer userId;
    @TableField(value = "wx_openid")
    private String wxOpenId;
    private String phone;
    @TableField(value = "nick_name")
    private String nickName;
    @TableField(value = "avatar_url")
    private String avatarUrl;
    @TableField(value = "entry_id")
    private Integer entryId;
    @TableField(value = "entry_datetime")
    private Date entryDatetime;
}
