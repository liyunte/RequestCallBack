package com.lyt.requestcallback.mylibrary.listener;

import java.util.Map;

public interface IHttpProcessor {
    /**
     * Post请求
     * @param url 地址
     * @param map 参数集合
     * @param clazz 反回数据bean的类型
     */
    void post(String url, Map<String,Object> map,Class<?> clazz);


}
