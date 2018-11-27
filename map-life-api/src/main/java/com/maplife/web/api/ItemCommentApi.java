package com.maplife.web.api;

import com.maplife.bo.ItemCommentBo;
import com.maplife.service.ItemCommentService;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.session.WebContext;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@RequiresAuthentication
@RequestMapping(value = "public/api")
@RestController
public class ItemCommentApi {

    @Autowired
    private ItemCommentService itemCommentService;
    @Autowired
    private WebContext webContext;

    @PostMapping(value = "/item/comment")
    @Log
    public JsonEntity createNewComment(@RequestBody ItemCommentBo itemCommentBo){
        Map<String, Object> result = new HashMap<String, Object>(2);
        itemCommentBo.setUserId(webContext.getCurrentUser().getUserId());
        result.put("commentId", itemCommentService.createNewComment(itemCommentBo));
        return ResponseHelper.createInstance(result);
    }
}
