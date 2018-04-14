package com.honeylovely.center.common.utils;



public class QueryParam {
    private String propertyName;
    private Object value;
    private OperateType operateType;
    private PredictionType predictionType;

    public QueryParam(String propertyName, Object value) {
        this.operateType = OperateType.EQ;
        this.predictionType = PredictionType.AND;
        this.propertyName = propertyName;
        this.value = value;
    }

    public QueryParam(String propertyName, OperateType operateType) {
        this.operateType = OperateType.EQ;
        this.predictionType = PredictionType.AND;
        this.propertyName = propertyName;
        this.operateType = operateType;
    }

    public QueryParam(String propertyName, Object value, OperateType operateType) {
        this.operateType = OperateType.EQ;
        this.predictionType = PredictionType.AND;
        this.propertyName = propertyName;
        this.value = value;
        this.operateType = operateType;
    }

    public QueryParam(String propertyName, Object value, OperateType operateType, PredictionType predictionType) {
        this.operateType = OperateType.EQ;
        this.predictionType = PredictionType.AND;
        this.propertyName = propertyName;
        this.value = value;
        this.operateType = operateType;
        this.predictionType = predictionType;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OperateType getOperateType() {
        return this.operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public PredictionType getPredictionType() {
        return this.predictionType;
    }

    public void setPredictionType(PredictionType predictionType) {
        this.predictionType = predictionType;
    }
}
