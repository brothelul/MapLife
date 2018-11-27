package com.maplife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maplife.entity.ItemContent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public interface ItemContentMapper extends BaseMapper<ItemContent> {
    @Update("UPDATE item_content SET delete_id=#{deleteId},delete_datetime=now() WHERE item_id=#{itemId}")
    void deleteByItemId(@Param(value = "itemId")Integer itemId, @Param(value = "deleteId")Integer deleteId);
}
