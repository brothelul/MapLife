package com.maplife.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxUserBo {
    private String wxCode;
    private String iv;
    private String encryptedData;
}
