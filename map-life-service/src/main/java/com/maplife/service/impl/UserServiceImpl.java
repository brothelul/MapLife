package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;
import com.maplife.mapper.UserMapper;
import com.maplife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@CacheConfig(cacheNames = "userServiceCache")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "#p0")
    @Override
    public User findUserByWxOpenId(String wxOpenId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User user = userMapper.selectOne(userQueryWrapper.eq("wx_openid", wxOpenId));
        return user;
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    @CachePut(key = "#p0")
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
