package com.maplife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @auther mosesc
 * @date 12/03/18.
 */
@Data
public class SysConfig {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String appName;
    private String configName;
    private String configValue;
    private Integer entryId;
    private Date entryDatetime;
}
