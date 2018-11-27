package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maplife.bo.InputItemBo;
import com.maplife.bo.ItemCategoryBo;
import com.maplife.bo.ItemContentBo;
import com.maplife.bo.OutputItemBo;
import com.maplife.bo.UserBo;
import com.maplife.constant.ContentType;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemCategory;
import com.maplife.entity.ItemContent;
import com.maplife.entity.User;
import com.maplife.exception.ResourceNotFoundException;
import com.maplife.exception.ValidationFailedException;
import com.maplife.mapper.ItemCategoryMapper;
import com.maplife.mapper.ItemContentMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.mapper.UserMapper;
import com.maplife.service.ItemService;
import com.maplife.util.ContentTypeConvertUtil;
import com.maplife.util.DateUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    private UserMapper userMapper;
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
            throw new ValidationFailedException("请选择发布类型");
        }
        ItemCategory itemCategory = itemCategoryMapper.selectById(categoryId);
        if (itemCategory == null){
            throw new ResourceNotFoundException("未查询到对应类型");
        }
        if (StringUtils.isEmpty(title)){
            throw new ValidationFailedException("标题不能为空");
        }
        cuteOffDate = cuteOffDate == null ? DateUtil.getDateAfterAdd(Calendar.DAY_OF_YEAR, SystemConstant.DEFAULT_CUTE_OFF_DAY) : cuteOffDate;
        Item item = new Item();
        BeanUtils.copyProperties(inputItemBo, item);
        item.setCuteOffDate(cuteOffDate);
        item.setEntryId(SystemConstant.SYSTEM_ID);
        item.setEntryDatetime(new Date());
        itemMapper.insert(item);
        Integer itemId = item.getItemId();
        List<ItemContentBo> itemContent = inputItemBo.getItemContent();
        if(inputItemBo.getItemContent() != null){
            itemContent.forEach(itemContentBo -> {
                String typeName = itemContentBo.getContentType();
                ItemContent content = new ItemContent();
                content.setItemId(itemId);
                content.setSeqNo(itemContentBo.getSeqNo());
                content.setEntryId(SystemConstant.SYSTEM_ID);
                content.setEntryDatetime(new Date());
                content.setContent(itemContentBo.getContent());
                content.setContentType(ContentTypeConvertUtil.typeTxt2TypeNo(typeName));
                itemContentMapper.insert(content);
            });
        }
    }

    @Override
    public void deleteItem(Integer itemId, Integer userId) {
        Item item = itemMapper.selectById(itemId);
        if (item == null || item.getDeleteId() != null){
            throw new ResourceNotFoundException("未查询到记录");
        }
        if (!item.getUserId().equals(userId)){
            throw new UnauthorizedException();
        }
        UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("delete_id", userId);
        updateWrapper.set("delete_datetime", new Date());
        updateWrapper.eq("id", itemId);
        itemMapper.update(item, updateWrapper);
        itemContentMapper.deleteByItemId(itemId, userId);
    }

    @Override
    public OutputItemBo getItem(Integer itemId) {
        Item item = itemMapper.selectById(itemId);
        if (item == null || item.getDeleteId() != null){
            throw new ResourceNotFoundException("未查询到记录");
        }
        OutputItemBo outputItemBo = new OutputItemBo();
        BeanUtils.copyProperties(item, outputItemBo);
        // 获取内容
        QueryWrapper<ItemContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        queryWrapper.isNull("delete_id");
        List<ItemContent> itemContents = itemContentMapper.selectList(queryWrapper);
        if (itemContents != null && itemContents.size() > 0){
            List<ItemContentBo> itemContentBos = new ArrayList<>();
            itemContents.forEach(itemContent -> {
                ItemContentBo itemContentBo = new ItemContentBo();
                BeanUtils.copyProperties(itemContent, itemContentBo);
                itemContentBo.setContentType(ContentTypeConvertUtil.typeNo2TypeTxt(itemContent.getContentType()));
                itemContentBos.add(itemContentBo);
            });
            outputItemBo.setItemContent(itemContentBos);
        }
        // 获取作者信息
        User user = userMapper.selectById(item.getUserId());
        UserBo userBo = new UserBo();
        BeanUtils.copyProperties(user, userBo);
        outputItemBo.setAuthor(userBo);
        // 分类信息
        ItemCategory category = itemCategoryMapper.selectById(item.getCategoryId());
        ItemCategoryBo categoryBo = new ItemCategoryBo();
        BeanUtils.copyProperties(category, categoryBo);
        outputItemBo.setItemCategory(categoryBo);
        // 统计浏览量
        viewCountAdd(itemId);
        return outputItemBo;
    }

    @Async
    private void viewCountAdd(Integer itemId) {
        itemMapper.addViewCount(itemId);
    }
}
