package com.honeylovely.center.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum ResponseCode {
    SUCCESS(200, "success"),
    NOT_LOGIN(303, "用户未登录"),

    REQUEST_ERROR(400, "request error"),

    NOT_AUTHORIZED(401, "not authorized"),
    PHONE_FAILED(4011, "手机号重复！"),
    NOT_SUPPORT_VERSION(4012, "not support this version"),
    MISS_USER(4013, "不存在的用户"),

    USERNAME_EXIST(1001, "用户名已经存在"),
    OLD_PASSWORD_ERROR(1002, "旧密码错误"),
    MULTIPLE_USER_NAME(4014,"存在多个相同名称的用户，请手动处理"),
    URL_EXIST(1010, "菜单URL已存在"),
    LEVEL_ERROR(1011, "菜单类型与上级菜单不匹配"),
    RES_KEY_EXIST(1012, "菜单Key已存在"),
    EXIST(1099, "已经存在"),
    MISS_REQUIRED(402, "miss required parameters"),
    SIGN_FAILED(403, "invalid sign"),

    SERVER_ERROR(500, "internal server error"),
    
    ;
    private int value;
    private String desc;

    ResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String toJson(){
        JSONArray jsonArray = new JSONArray();
        for (ResponseCode e : ResponseCode.values()) {
            JSONObject object = new JSONObject();
            object.put("value", e.getValue());
            object.put("desc", e.getDesc());
            jsonArray.add(object);
        }
        return jsonArray.toString();
    }
}
