package com.maplife.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @auther mosesc
 * @date 11/26/18.
 */
@Data
public class ItemBo {
    private String title;
    private Date cuteOffDate;
    private String locationName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private List<ItemContentBo> itemContent;
}
