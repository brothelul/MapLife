package com.maplife.service;

import com.maplife.bo.WxUserBo;
import com.maplife.entity.User;

public interface WxService {
    User getUserInfo(WxUserBo wxUserBo);
}
