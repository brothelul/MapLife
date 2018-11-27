package com.maplife.service;

import com.maplife.bo.InputItemBo;
import com.maplife.bo.OutputItemBo;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
public interface ItemService {
    void createItem(InputItemBo inputItemBo);

    void deleteItem(Integer itemId, Integer userId);

    OutputItemBo getItem(Integer itemId);
}
