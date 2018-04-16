package indi.dsb.hardware.common.utils;


public enum OperateType {
    EQ("EQ", "="),
    NE("NE", "<>"),
    LE("LE", "<="),
    LT("LT", "<"),
    GE("GE", ">="),
    GT("GT", ">"),
    LIKE("LIKE", "LIKE"),
    ISNULL("ISNULL", "is null"),
    ISNOTNULL("ISNOTNULL", "is not null"),
    ISTRUE("ISTRUE", "is true"),
    ISFALSE("ISFALSE", "is false");

    public final String value;
    public final String operater;

    private OperateType(String value, String operater) {
        this.value = value;
        this.operater = operater;
    }
}
