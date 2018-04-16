package indi.dsb.hardware.common.type;

/**
 * @Title: MatterGrade.java
 * @Package com.honeylovely.center.common.type
 * @Description: 处理等级 1：低级 2：中级 3：紧急 4：致命
 * @author dsb
 * @date 2018年3月21日 下午2:12:13
 */
public enum MatterGrade {
    LEVEL_1(1, "低级", 24*15), // 15个工作日内处理
    LEVEL_2(2, "中级", 24*7), // 7个工作日内处理
    LEVEL_3(3, "紧急", 24*3), // 3个工作日内处理
    LEVEL_4(4, "致命", 24), // 24小时内处理
    ;

    private int code;
    private String value;
    private int processingTime; // 处理时间(小时) 问题创建时 处理时间默认为 当前时间+此时间
    
    public static MatterGrade getByCode(int code){
        for (MatterGrade status : MatterGrade.values()) {
            if (code == status.code) {
                return status;
            }
        }
        return null;
    }

    private MatterGrade(int code, String value, int processingTime) {
        this.code = code;
        this.value = value;
        this.processingTime = processingTime;
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

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

}
