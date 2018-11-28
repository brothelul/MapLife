package com.maplife.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.maplife.entity.ItemComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public interface ItemCommentMapper extends BaseMapper<ItemComment> {

    @Update("UPDATE item_comment SET delete_id=#{userId}, delete_datetime=now() WHERE id=#{commentId}")
    int deleteById(@Param("commentId") Integer commentId, @Param("userId") Integer userId);
}
