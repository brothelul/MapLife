package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maplife.entity.SysConfig;
import com.maplife.mapper.SysConfigMapper;
import com.maplife.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther mosesc
 * @date 12/03/18.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public String getConfigValue(String appName, String configName) {
        QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_name", appName);
        queryWrapper.eq("config_name", configName);
        SysConfig sysConfig = sysConfigMapper.selectOne(queryWrapper);
        return sysConfig == null ? null : sysConfig.getConfigValue();
    }
}
