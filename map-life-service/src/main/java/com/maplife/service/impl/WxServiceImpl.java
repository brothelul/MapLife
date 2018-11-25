package com.maplife.service.impl;

import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;
import com.maplife.service.WxService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements WxService {
    @Override
    public User getUserInfo(WxUserBo wxUserBo) {
        User user = new User();
        user.setAvatarUrl("dasdsa");
        user.setNickName("dasdaasdsa");
        user.setWxOpenId("test123");
        return user;
    }
}
