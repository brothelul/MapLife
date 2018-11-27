package com.maplife.constant;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
public enum AppType {
    ADMIN(0), MAP_LIFE(1);
    private int seqNo;

    AppType(int seqNo){
        this.seqNo = seqNo;
    }

    public int getSeqNo() {
        return seqNo;
    }
}
