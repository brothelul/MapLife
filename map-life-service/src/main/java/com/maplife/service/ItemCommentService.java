package com.maplife.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maplife.bo.ItemCommentBo;
import com.maplife.bo.OutputItemCommentBo;
import com.maplife.bo.PageQueryBo;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
public interface ItemCommentService {
    Integer createNewComment(ItemCommentBo itemCommentBo);

    Page<OutputItemCommentBo> listComment(PageQueryBo pageQueryBo);

    void deleteComment(Integer commentId, Integer userId);
}
