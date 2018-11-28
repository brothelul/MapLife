package com.maplife.web.api;

import com.maplife.bo.ItemCommentBo;
import com.maplife.bo.PageQueryBo;
import com.maplife.service.ItemCommentService;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.session.WebContext;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Api
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

    @DeleteMapping(value = "/item/comment/{commentId}")
    @Log
    public JsonEntity deleteComment(@PathVariable("commentId") Integer commentId){
        Integer userId = webContext.getCurrentUser().getUserId();
        itemCommentService.deleteComment(commentId, userId);
        return ResponseHelper.createMessageInstance("删除成功");
    }

    @PostMapping(value = "/item/{itemId}/comments")
    @Log
    public JsonEntity listItemComment(@PathVariable("itemId") Integer itemId, @RequestBody PageQueryBo pageQueryBo){
        Map<String, Object> condition = pageQueryBo.getCondition();
        condition.put("item_id", itemId);
        return ResponseHelper.createInstance(itemCommentService.listComment(pageQueryBo));
    }
}
