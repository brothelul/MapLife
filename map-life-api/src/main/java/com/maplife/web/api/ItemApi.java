package com.maplife.web.api;

import com.maplife.bo.InputItemBo;
import com.maplife.bo.OutputItemBo;
import com.maplife.constant.LogType;
import com.maplife.service.ItemService;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.session.WebContext;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Api
@RestController
@RequestMapping(value = "/public/api")
@RequiresAuthentication
public class ItemApi {
    @Autowired
    private ItemService itemService;
    @Autowired
    private WebContext webContext;

    @PostMapping(value = "/item")
    @Log(action = LogType.ITEM)
    public JsonEntity createItem(@RequestBody InputItemBo inputItemBo){
        inputItemBo.setUserId(webContext.getCurrentUser().getUserId());
        itemService.createItem(inputItemBo);
        return ResponseHelper.createMessageInstance("创建成功");
    }

    @DeleteMapping(value = "/item/{itemId}")
    @Log(action = LogType.ITEM)
    public JsonEntity deleteItem(@PathVariable("itemId") Integer itemId){
        itemService.deleteItem(itemId, webContext.getCurrentUser().getUserId());
        return ResponseHelper.createMessageInstance("删除成功");
    }

    @GetMapping(value = "/item/{itemId}")
    @Log(action = LogType.ITEM)
    public JsonEntity<OutputItemBo> getItem(@PathVariable(value = "itemId") Integer itemId){
        return ResponseHelper.createInstance(itemService.getItem(itemId));
    }
}
