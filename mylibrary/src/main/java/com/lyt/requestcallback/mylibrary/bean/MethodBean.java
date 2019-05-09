package com.lyt.requestcallback.mylibrary.bean;

import java.lang.reflect.Method;

public class MethodBean {
    /**
     * 方法参数1
     */
    private Class<?> type;

    /**
     * 注解参数 请求标记
     */
    private String value;


    /**
     * 方法
     */
    private Method method;


    public MethodBean() {
    }

    public MethodBean(Class<?> type, String value, Method method) {
        this.type = type;
        this.value = value;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
