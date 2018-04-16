package indi.dsb.hardware.common.type;

/**
 * @Title: ProjectMemberType.java
 * @Package com.honeylovely.center.common.type
 * @Description: 成员类型1:项目经理 2:普通成员
 * @author dsb
 * @date 2018年3月21日 下午2:12:13
 */
public enum ProjectMemberType {
    PRODUCT_USER(1, "项目经理"), 
    MEMBER_USER(2, "普通成员"),
    ;

    private int code;
    private String value;
    
    public static String getDesc(int code){
        for (ProjectMemberType status : ProjectMemberType.values()) {
            if (code == status.code) {
                return status.value;
            }
        }
        return null;
    }

    private ProjectMemberType(int code, String value) {
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
