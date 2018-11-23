package com.maplife.constant;

/**
 * @auther mosesc
 * @date 11/23/18.
 */
public enum LogType {
    COMMON(0),LOGIN(1);
    private int seqNo;
    private LogType(int seqNo){
        this.seqNo = seqNo;
    }
}
