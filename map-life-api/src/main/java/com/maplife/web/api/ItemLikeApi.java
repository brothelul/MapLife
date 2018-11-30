package com.maplife.web.api;

import com.maplife.bo.InputItemLikeBo;
import com.maplife.service.ItemLikeService;
import com.maplife.web.session.WebContext;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Api
@RequiresAuthentication
@RequestMapping(value = "public/api/")
@RestController
public class ItemLikeApi {
    @Autowired
    private ItemLikeService itemLikeService;
    @Autowired
    private WebContext webContext;

    @RequestMapping(value = "/common/like")
    public JsonEntity addNewLike(@RequestBody InputItemLikeBo inputItemLike){
        inputItemLike.setUserId(webContext.getCurrentUser().getUserId());
        itemLikeService.addNewLike(inputItemLike);
        return ResponseHelper.createInstance("喜欢成功");
    }
}
