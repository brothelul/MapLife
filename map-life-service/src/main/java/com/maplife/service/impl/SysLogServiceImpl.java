package com.maplife.service.impl;

import com.maplife.entity.SysLog;
import com.maplife.mapper.SysLogMapper;
import com.maplife.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Async
    @Override
    public void saveSysLog(SysLog log) {
        sysLogMapper.insert(log);
    }
}
