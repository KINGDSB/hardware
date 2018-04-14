package com.honeylovely.center.common.type;

/**
 * @Title: MatterStatus.java
 * @Package com.honeylovely.center.common.type
 * @Description: 问题状态 1:待处理 2：处理中 3：待确认 4：未解决 5：已解决 6：申请延期中
 * @author dsb
 * @date 2018年3月21日 下午2:12:13
 */
public enum MatterStatus {
    WAIT_HANDLE(1, "待处理"), 
    PROCESSING(2, "处理中"), 
    CONFIRM(3, "待确认"), 
    UNSOLVED(4, "未解决"), 
    RESOLVED(5, "已解决"), 
    APPLY_DELAYING(6, "延期申请中"),;

    private int code;
    private String value;
    
    public static String getDesc(int code){
        for (MatterStatus status : MatterStatus.values()) {
            if (code == status.code) {
                return status.value;
            }
        }
        return null;
    }

    private MatterStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
