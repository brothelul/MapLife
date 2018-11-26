package com.maplife.service.impl;

import com.maplife.bo.InputItemBo;
import com.maplife.bo.InputItemContentBo;
import com.maplife.bo.ItemContentBo;
import com.maplife.constant.ContentType;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemCategory;
import com.maplife.entity.ItemContent;
import com.maplife.exception.ServiceException;
import com.maplife.mapper.ItemCategoryMapper;
import com.maplife.mapper.ItemContentMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.service.ItemService;
import com.maplife.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ItemContentMapper itemContentMapper;

    @Override
    public void createItem(InputItemBo inputItemBo) {
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
        List<ItemContentBo> itemContent = inputItemBo.getItemContent();
        if(inputItemBo.getItemContent() != null){
            itemContent.forEach(itemContentBo -> {
                String typeName = itemContentBo.getContentType();
                if (ContentType.IMAGE.getName().equals(typeName) || ContentType.TEXT.getName().equals(typeName)){
                    ItemContent content = new ItemContent();
                    content.setItemId(itemId);
                    content.setEntryId(SystemConstant.SYSTEM_ID);
                    content.setEntryDatetime(new Date());
                    content.setContent(itemContentBo.getContent());
                    Integer contentType = ContentType.TEXT.getName().equals(typeName) ? ContentType.TEXT.getSeqNo() : ContentType.IMAGE.getSeqNo();
                    content.setContentType(contentType);
                    itemContentMapper.insert(content);
                }
            });
        }
    }
}
