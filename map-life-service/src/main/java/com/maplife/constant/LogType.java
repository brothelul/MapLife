package com.maplife.constant;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
public enum LogType {
    COMMON(0),LOGIN(1),ITEM(2), WX(3);
    private int seqNo;
    LogType(int seqNo){
        this.seqNo = seqNo;
    }

    public int getSeqNo() {
        return seqNo;
    }
}
