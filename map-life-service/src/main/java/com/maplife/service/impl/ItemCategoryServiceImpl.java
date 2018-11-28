package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maplife.bo.ItemCategoryBo;
import com.maplife.entity.ItemCategory;
import com.maplife.mapper.ItemCategoryMapper;
import com.maplife.service.ItemCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<ItemCategoryBo> getAllCategory() {
        QueryWrapper<ItemCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("active", 'Y');
        queryWrapper.isNull("delete_id");
        List<ItemCategory> categories = itemCategoryMapper.selectList(queryWrapper);
        if (categories == null || categories.size() == 0){
            return null;
        }
        List<ItemCategoryBo> itemCategoryBos = new ArrayList<>();
        categories.forEach(itemCategory -> {
            ItemCategoryBo itemCategoryBo = new ItemCategoryBo();
            BeanUtils.copyProperties(itemCategory, itemCategoryBo);
            itemCategoryBos.add(itemCategoryBo);
        });
        return itemCategoryBos;
    }
}
