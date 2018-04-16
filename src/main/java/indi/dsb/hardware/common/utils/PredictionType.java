package indi.dsb.hardware.common.utils;

public enum PredictionType {
    AND("AND"),
    OR("OR");

    public final String value;

    PredictionType(String value) {
        this.value = value;
    }
}
