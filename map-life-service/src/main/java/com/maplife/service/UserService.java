package com.maplife.service;

import com.maplife.bo.Token;
import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;

public interface UserService {
    Token login(WxUserBo wxUser);
}
