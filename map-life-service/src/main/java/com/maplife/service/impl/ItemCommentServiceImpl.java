package com.maplife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maplife.bo.ItemCommentBo;
import com.maplife.bo.OutputItemCommentBo;
import com.maplife.bo.PageQueryBo;
import com.maplife.bo.PageQuerySorterBo;
import com.maplife.bo.UserBo;
import com.maplife.constant.SystemConstant;
import com.maplife.entity.Item;
import com.maplife.entity.ItemComment;
import com.maplife.entity.User;
import com.maplife.exception.ResourceNotFoundException;
import com.maplife.mapper.ItemCommentMapper;
import com.maplife.mapper.ItemMapper;
import com.maplife.mapper.UserMapper;
import com.maplife.service.ItemCommentService;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Service
public class ItemCommentServiceImpl implements ItemCommentService {

    @Autowired
    private ItemCommentMapper itemCommentMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private UserMapper userMapper;

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
        return itemComment.getCommentId();
    }

    @Override
    public Page<OutputItemCommentBo> listComment(PageQueryBo pageQueryBo){
        Page<ItemComment> itemCommentPage = new Page<>();
        long size = pageQueryBo.getSize();
        long current = pageQueryBo.getCurrent();
        itemCommentPage.setCurrent(current);
        itemCommentPage.setSize(size);
        // 设置查询条件
        QueryWrapper<ItemComment> queryWrapper = new QueryWrapper<>();
        Set<Map.Entry<String, Object>> conditionEntrySet = pageQueryBo.getCondition().entrySet();
        if (conditionEntrySet.size() > 0){
            conditionEntrySet.forEach(entry -> {
                queryWrapper.eq(entry.getKey(), entry.getValue());
            });
        }
        // 排序条件
        PageQuerySorterBo sorter = pageQueryBo.getSorter();
        if (sorter != null){
            String[] columns = sorter.getColumns();
            if (columns.length > 0){
                queryWrapper.orderBy(true, sorter.getAsc(), columns);
            }
        }
        // 查询结果，赋值
        itemCommentMapper.selectPage(itemCommentPage, queryWrapper);
        List<ItemComment> itemComments = itemCommentPage.getRecords();
        Page<OutputItemCommentBo> itemCommentBoPage = new Page<>();
        if (itemComments != null && itemComments.size() > 0){
            List<OutputItemCommentBo> itemCommentBos = new ArrayList<>();
            itemComments.forEach(itemComment -> {
                OutputItemCommentBo itemCommentBo = new OutputItemCommentBo();
                BeanUtils.copyProperties(itemComment, itemCommentBo);
                User user = userMapper.selectById(itemComment.getUserId());
                UserBo userBo = new UserBo();
                BeanUtils.copyProperties(user, userBo);
                itemCommentBo.setUser(userBo);
                itemCommentBos.add(itemCommentBo);
            });
            itemCommentBoPage.setRecords(itemCommentBos);
        }
        itemCommentBoPage.setSize(size);
        itemCommentBoPage.setCurrent(current);
        itemCommentBoPage.setTotal(itemCommentPage.getTotal());
        return itemCommentBoPage;
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
