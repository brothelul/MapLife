package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maplife.bo.InputItemLikeBo;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemComment;
import com.maplife.entity.ItemLike;
import com.maplife.exception.ResourceNotFoundException;
import com.maplife.exception.ValidationFailedException;
import com.maplife.mapper.ItemCommentMapper;
import com.maplife.mapper.ItemLikeMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.service.ItemLikeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/30/18.
 */
@Service
public class ItemLikeServiceImpl implements ItemLikeService {

    @Autowired
    private ItemLikeMapper itemLikeMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemCommentMapper itemCommentMapper;

    @Override
    public void addNewLike(InputItemLikeBo inputItemLike) {
        Integer typeNo = inputItemLike.getLikeType();
        Integer targetNo = inputItemLike.getTargetId();
        boolean typeNoExistCondition = (typeNo == null || (!typeNo.equals(0) && !typeNo.equals(1)));
        if (typeNoExistCondition){
            inputItemLike.setLikeType(0);
        }
        if (targetNo == null){
            throw new ValidationFailedException("");
        }
        QueryWrapper<ItemLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("like_type", typeNo);
        queryWrapper.eq("target_id", targetNo);
        ItemLike itemLike = itemLikeMapper.selectOne(queryWrapper);
        if (itemLike != null){
            return;
        }
        // query item
        if (typeNo.equals(0)){
            Item item = itemMapper.selectById(targetNo);
            if (item == null || item.getDeleteId() != null){
                throw new ResourceNotFoundException("记录不存在");
            }
        } else {
            ItemComment itemComment = itemCommentMapper.selectById(targetNo);
            if (itemComment == null || itemComment.getDeleteId() != null){
                throw new ResourceNotFoundException("评论不存在");
            }
        }
        itemLike = new ItemLike();
        BeanUtils.copyProperties(inputItemLike, itemLike);
        itemLike.setEntryId(SystemConstant.SYSTEM_ID);
        itemLike.setEntryDatetime(new Date());
        itemLikeMapper.insert(itemLike);
    }
}
