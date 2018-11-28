package com.maplife.constant;

/**
 * @auther mosesc
 * @date 11/28/18.
 */
public enum LikeType {
    ITEM("item", 0), COMMENT("comment", 1);

    private String name;
    private int seqNo;

    LikeType(String name, int seqNo){
        this.name = name;
        this.seqNo = seqNo;
    }

    public String getName() {
        return name;
    }

    public int getSeqNo() {
        return seqNo;
    }
}
