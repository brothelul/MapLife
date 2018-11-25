package com.maplife.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestUserBo {
    private String wxOpenId;
    private String nickName;
    private String avatarUrl;
}
