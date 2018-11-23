package com.maplife.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Token {
    private String token;
    private Integer expire;
}
