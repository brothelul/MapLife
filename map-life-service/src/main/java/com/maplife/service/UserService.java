package com.maplife.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;

public interface UserService {
    User findUserByWxOpenId(String wxOpenId);

    void saveUser(User user);

    void updateUser(User user, Wrapper<User> updateFields);
}
