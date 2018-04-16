package indi.dsb.hardware.common.type;

/**
 * @Title: ReplyType.java
 * @Package com.honeylovely.center.common.type
 * @Description: 回复类型 1:项目经理回复 2:处理人回复
 * @author dsb
 * @date 2018年3月21日 下午2:12:13
 */
public enum ReplyType {
    PRODUCT_USER_REPLY(1, "项目经理回复"), 
    PROCESS_USER_REPLY(2, "处理人回复"),
    ;

    private int code;
    private String value;
    
    public static String getDesc(int code){
        for (ReplyType status : ReplyType.values()) {
            if (code == status.code) {
                return status.value;
            }
        }
        return null;
    }

    private ReplyType(int code, String value) {
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
