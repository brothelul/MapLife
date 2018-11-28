package com.maplife.bo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
@Data
public class PageQueryBo {
    private long current;
    private long size;
    private Map<String, Object> condition = new HashMap<>(2);
    private PageQuerySorterBo sorter;
}
