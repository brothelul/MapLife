package com.maplife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maplife.entity.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public interface ItemMapper extends BaseMapper<Item> {
    @Update("UPDATE item SET view_count=view_count+1 WHERE id=#{itemId}")
    void addViewCount(@Param("itemId")Integer itemId);
}
