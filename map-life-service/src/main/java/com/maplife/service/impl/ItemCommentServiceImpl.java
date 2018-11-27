package com.maplife.service.impl;

import com.maplife.bo.ItemCommentBo;
import com.maplife.mapper.ItemCommentMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.service.ItemCommentService;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
    }
}
