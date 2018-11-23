package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @auther mosesc
 * @date 11/23/18.
 */

@Data
@TableName(value = "sys_log")
public class SysLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer logId;
    @TableField(value = "app_id")
    private Integer appId;
    private Integer logType;
}
