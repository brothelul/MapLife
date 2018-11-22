package com.maplife.service.impl;

import com.maplife.bo.Token;
import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;
import com.maplife.mapper.UserMapper;
import com.maplife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Token login(WxUserBo wxUser) {
        User user = new User();
        user.setEntryId(-9999);
        user.setEntryDatetime(new Date());
        user.setWxOpenId("test");
        user.setNickName("test");
        user.setAvatarUrl("test");
        userMapper.insert(user);
        return new Token();
    }
}
