package com.honeylovely.center.common.utils;

import com.honeylovely.center.common.ResponseCode;
import com.google.protobuf.GeneratedMessage;

import java.io.IOException;

/**
 * Created by ryan on 12/25/15.
 */
public class Response {
    private Integer code;
    private String desc;
    private Object result;
    private long timestamp = System.currentTimeMillis();

    public Response(ResponseCode responseCode) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
    }

    public Response(ResponseCode responseCode, String desc) {
        this.code = responseCode.getValue();
        this.desc = desc;
    }

    public Response(ResponseCode responseCode, Object result) {
        this.code = responseCode.getValue();
        this.desc = responseCode.getDesc();
        this.result = result;
    }

    public Response(Exception cause) {
        this.code = ResponseCode.SERVER_ERROR.getValue();
        this.desc = ResponseCode.SERVER_ERROR.getDesc();
        this.result = cause.getMessage();
    }

    public Response(Object result) {
        this.code = ResponseCode.SUCCESS.getValue();
        this.desc = ResponseCode.SUCCESS.getDesc();
        this.result = result;
    }

    public Response(Integer code, String desc, Object result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJson() {
        try {
            if (this.result instanceof GeneratedMessage) {
                result = JsonUtils.toObject(JsonUtils.jsonProto((GeneratedMessage) result), result.getClass());
            }
            return JsonUtils.toJson(this);
        } catch (IOException e) {
            return ResponseCode.SERVER_ERROR.getDesc();
        }
    }
}
