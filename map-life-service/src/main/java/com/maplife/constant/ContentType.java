package com.maplife.constant;

import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text", 0), IMAGE("image", 1);

    private String name;
    private Integer seqNo;

    ContentType(String name, Integer seqNo){
        this.name = name;
        this.seqNo = seqNo;
    }

}
