package com.lyt.requestcallback.mylibrary.bean;

import java.lang.reflect.Method;

public class MethodBean {
    /**
     * 方法参数1
     */
    private Class<?> type;

    /**
     * 注解参数
     */
    private String requestType;

    /**
     * 方法
     */
    private Method method;



    public MethodBean(Class<?> type, String requestType, Method method) {
        this.type = type;
        this.requestType = requestType;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
