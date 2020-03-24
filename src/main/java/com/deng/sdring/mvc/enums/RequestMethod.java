package com.deng.sdring.mvc.enums;

/**
 * Created by Administrator on 2020/3/24.
 */
public enum  RequestMethod {
    GET("GET"),

    HEAD("HEAD"),

    POST("POST"),

    PUT("PUT"),

    DELETE("DELETE");

    private String value;

    RequestMethod(String value) {
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    public static RequestMethod getEnum(String value) {
        for (RequestMethod requestMethod : RequestMethod.values()) {
            if (requestMethod.getValue().equals(value.toUpperCase())) {
                return requestMethod;
            }
        }
        return null;
    }
}
