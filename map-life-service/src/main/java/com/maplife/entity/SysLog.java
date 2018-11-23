package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @auther mosesc
 * @date 11/23/18.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer logId;
    private Integer appId;
    private Integer logType;
    private String methodName;
    private Integer userId;
    private String requestUrl;
    private String inParams;
    private String outParams;
    private String exception;
    private String exceptionMsg;
    private String token;
    private String ipAddress;
    private Date startDatetime;
    private Date endDatetime;
    private Integer entryId;
    private Date entryDatetime;
}
