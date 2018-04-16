package com.honeylovely.center.common.type;

/**
 * @Title: ReplyType.java
 * @Package com.honeylovely.center.common.type
 * @Description: 回复类型 1:项目经理回复 2:处理人回复
 * @author dsb
 * @date 2018年3月21日 下午2:12:13
 */
public enum ReplyStatus {
    PENDING(1, "未处理"), 
    HANDLED(2, "已处理"),
    ;

    private int code;
    private String value;
    
    public static String getDesc(int code){
        for (ReplyStatus status : ReplyStatus.values()) {
            if (code == status.code) {
                return status.value;
            }
        }
        return null;
    }

    private ReplyStatus(int code, String value) {
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
