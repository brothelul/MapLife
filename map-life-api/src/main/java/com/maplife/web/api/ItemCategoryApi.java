package com.maplife.web.api;

import com.maplife.service.ItemCategoryService;
import com.maplife.web.framework.annotation.Log;
import com.maplife.web.util.JsonEntity;
import com.maplife.web.util.ResponseHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Api
@RequiresAuthentication
@RequestMapping("public/api/")
@RestController
public class ItemCategoryApi {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping(value = "/item/categories")
    @Log
    public JsonEntity listAllCategory(){
        return ResponseHelper.createInstance(itemCategoryService.getAllCategory());
    }
}
