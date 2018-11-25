package com.maplife.service;

import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;

public interface UserService {
    User findUserByWxOpenId(String wxOpenId);

    void saveUser(User user);

    void updateUser(User user);
}
