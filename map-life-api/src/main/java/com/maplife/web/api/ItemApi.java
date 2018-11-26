package com.maplife.web.api;

import com.maplife.bo.InputItemBo;
import com.maplife.service.ItemService;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Api
@RestController
@RequestMapping(value = "/public/api")
public class ItemApi {
    @Autowired
    private ItemService itemService;

    @RequiresAuthentication
    @PostMapping(value = "/newItem")
    public JsonEntity createItem(@RequestBody InputItemBo inputItemBo){
        itemService.createItem(inputItemBo);
        return ResponseHelper.createMessageInstance("创建成功");
    }
}
