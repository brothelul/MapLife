package com.maplife.web.api;

import com.maplife.bo.TestUserBo;
import com.maplife.bo.WxUserBo;
import com.maplife.constant.LogType;
import com.maplife.entity.User;
import com.maplife.service.UserService;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/public/api")
@Api
public class AccountApi {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/wx/login")
    @Log(action = LogType.LOGIN)
    public JsonEntity<Map<String, String>> getWxAccessToken(@RequestBody WxUserBo wxUserBo, HttpServletRequest request){
        userLogin(wxUserBo);
        Map<String, String> data = new HashMap<String, String>();
        data.put("sessionId", request.getSession().getId());
        return ResponseHelper.createInstance(data);
    }

    private void userLogin(WxUserBo wxUserBo){
        String wxOpenId = wxUserBo.getIv();
        User user = userService.findUserByWxOpenId(wxOpenId);
        if (user == null){
        }
    }

    @PostMapping(value = "/wx/login/as")
    @Log(action = LogType.LOGIN)
    public JsonEntity<Map<String, String>> getWxAccessToken(@RequestBody TestUserBo testUserBo, HttpServletRequest request){
        userLoginAs(testUserBo);
        Map<String, String> data = new HashMap<String, String>();
        data.put("sessionId", request.getSession().getId());
        return ResponseHelper.createInstance(data);
    }

    public void userLoginAs(TestUserBo testUserBo){
        String wxOpenId = testUserBo.getWxOpenId();
        User user = userService.findUserByWxOpenId(wxOpenId);
        if (user == null){
            user = new User();
            user.setWxOpenId(wxOpenId);
            user.setNickName(testUserBo.getNickName());
            user.setAvatarUrl(testUserBo.getAvatarUrl());
            user.setEntryId(-9999);
            user.setEntryDatetime(new Date());
            userService.saveUser(user);
        } else {
            user.setNickName(testUserBo.getNickName());
            user.setAvatarUrl(testUserBo.getAvatarUrl());
            userService.updateUser(user);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(wxOpenId, (String)null);
        SecurityUtils.getSubject().login(token);
    }
}
