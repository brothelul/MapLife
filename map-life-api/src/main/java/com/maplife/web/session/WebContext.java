package com.maplife.web.session;

import com.maplife.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

@Component
public class WebContext {

    public User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
