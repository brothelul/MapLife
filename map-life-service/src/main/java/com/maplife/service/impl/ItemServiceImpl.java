package com.maplife.service.impl;

import com.maplife.bo.InputItemBo;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemCategory;
import com.maplife.exception.ServiceException;
import com.maplife.mapper.ItemCategoryMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.service.ItemService;
import com.maplife.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    public Integer createItem(InputItemBo inputItemBo) {
        Integer categoryId = inputItemBo.getCategoryId();
        String title = inputItemBo.getTitle();
        Date cuteOffDate = inputItemBo.getCuteOffDate();
        if (categoryId == null){
            throw new ServiceException("请选择发布类型");
        }
        ItemCategory itemCategory = itemCategoryMapper.selectById(categoryId);
        if (itemCategory == null){
            throw new ServiceException("未查询到对应类型");
        }
        if (StringUtils.isEmpty(title)){
            throw new ServiceException("标题不能为空");
        }
        cuteOffDate = cuteOffDate == null ? DateUtil.getDateAfterAdd(Calendar.DAY_OF_YEAR, SystemConstant.DEFAULT_CUTE_OFF_DAY) : cuteOffDate;
        Item item = new Item();
        BeanUtils.copyProperties(inputItemBo, item);
        item.setEntryId(SystemConstant.SYSTEM_ID);
        item.setEntryDatetime(new Date());
        Integer itemId = itemMapper.insert(item);
        return null;
    }
}
