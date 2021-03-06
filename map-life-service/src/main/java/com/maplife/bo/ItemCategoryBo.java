package com.maplife.bo;

import lombok.Data;

/**
 * @auther mosesc
 * @date 11/27/18.
 */
@Data
public class ItemCategoryBo {
    private Integer categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private Character active;
}
