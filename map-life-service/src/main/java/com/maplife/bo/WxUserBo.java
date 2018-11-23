package com.maplife.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WxUserBo {
    private String wxCode;
    private String iv;
    private String encryptedData;
}
