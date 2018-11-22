package com.maplife.web.api;

import com.maplife.bo.Token;
import com.maplife.bo.WxUserBo;
import com.maplife.service.UserService;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/api")
@Api
public class TokenApi {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/wx/token")
    public JsonEntity<Token> getWxAccessToken(WxUserBo wxUserBo){
        return ResponseHelper.createInstance(userService.login(wxUserBo));
    }
}
