package com.maplife.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maplife.bo.WxUserBo;
import com.maplife.constant.AppType;
import com.maplife.constant.LogType;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.SysLog;
import com.maplife.entity.User;
import com.maplife.exception.ServiceException;
import com.maplife.service.SysConfigService;
import com.maplife.service.SysLogService;
import com.maplife.service.WxService;
import com.maplife.util.AesCbcUtil;
import com.maplife.util.HttpUtil;
import com.maplife.util.StackTraceUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxServiceImpl implements WxService {
    private static Logger logger = Logger.getLogger(WxService.class);
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public User getUserInfo(WxUserBo wxUser) {
        String appId = sysConfigService.getConfigValue(SystemConstant.APP_WX, SystemConstant.WX_APP_ID);
        String secret = sysConfigService.getConfigValue(SystemConstant.APP_WX, SystemConstant.WX_SECRET);
        String host = sysConfigService.getConfigValue(SystemConstant.APP_WX, SystemConstant.WX_HOST);
        final String jsCode = wxUser.getWxCode();
        String data = null;
        String url = null;
        String exception = null;
        String exceptionMsg = null;
        Date startDate = new Date(System.currentTimeMillis());
        String sessionKey = null;
        String openId = null;
        // 请求微信API获取session和用户openid
        try {
            url = host+"?appid="+appId+"&secret="+secret+"&js_code="+jsCode+"&grant_type=authorization_code";
            data = HttpUtil.doGet(url);
            JSONObject object = JSON.parseObject(data);
            sessionKey = object.getString("session_key");
            openId = object.getString("openid");
            if (sessionKey == null || openId == null){
                throw new ServiceException("请求微信用户信息失败，返回数据："+data);
            }
        } catch (Exception e){
            exception = e.getMessage();
            exceptionMsg = StackTraceUtil.stackTrace2String(e);
            throw new ServiceException("请求微信API获取用户信息失败", e);
        } finally {
            SysLog sysLog = new SysLog(null, AppType.MAP_LIFE.getSeqNo(), LogType.WX.getSeqNo(), "com.maplife.service.impl.WxService.getUserInfo", null, url, null, data, exception, exceptionMsg, null, null, startDate, new Date(System.currentTimeMillis()), SystemConstant.SYSTEM_ID, new Date());
            sysLogService.saveSysLog(sysLog);
        }

        // 用session解析用户信息
        try {
            String result = AesCbcUtil.decrypt(wxUser.getEncryptedData(), sessionKey, wxUser.getIv(), SystemConstant.ENCODE_UTF8);
            logger.info("parse user info result: " +result);
            JSONObject userInfo = JSON.parseObject(result);
            User user = new User();
            user.setNickName(userInfo.getString("nickName"));
            user.setWxOpenId(openId);
            user.setAvatarUrl(userInfo.getString("avatarUrl"));
            return user;
        } catch (Exception e){
            logger.error("解析用户信息出错", e);
            throw new ServiceException("解析用户信息出错");
        }
    }
}
