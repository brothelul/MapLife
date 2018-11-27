package com.maplife.service.impl;

import com.maplife.bo.ItemCommentBo;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemComment;
import com.maplife.entity.ItemContent;
import com.maplife.exception.ResourceNotFoundException;
import com.maplife.mapper.ItemCommentMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.service.ItemCommentService;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public class ItemCommentServiceImpl implements ItemCommentService {

    @Autowired
    private ItemCommentMapper itemCommentMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Integer createNewComment(ItemCommentBo itemCommentBo) {
        Integer itemId = itemCommentBo.getItemId();
        Item item = itemMapper.selectById(itemId);
        if (item == null || item.getDeleteId() != null){
            throw new ResourceNotFoundException("未查询到记录");
        }
        Integer parentId = itemCommentBo.getParentId();
        if (parentId != null){
            ItemComment itemComment = itemCommentMapper.selectById(parentId);
            if (itemComment == null || itemComment.getDeleteId() != null){
                itemCommentBo.setParentId(null);
            }
        }
        ItemComment itemComment = new ItemComment();
        BeanUtils.copyProperties(itemCommentBo, itemComment);
        itemComment.setEntryId(SystemConstant.SYSTEM_ID);
        itemComment.setEntryDatetime(new Date());
        itemCommentMapper.insert(itemComment);
        return itemComment.getItemId();
    }

    @Override
    public void deleteComment(Integer commentId, Integer userId) {
        ItemComment itemComment = itemCommentMapper.selectById(commentId);
        if (itemComment == null || itemComment.getDeleteId() != null){
            throw new ResourceNotFoundException("未查询到评论");
        }
        if (!itemComment.getUserId().equals(userId) && userId > 0){
            throw new UnauthenticatedException();
        }
        itemCommentMapper.deleteById(commentId, userId);
    }
}
