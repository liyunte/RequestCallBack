package com.lyt.requestcallback.mylibrary;

import com.lyt.requestcallback.mylibrary.listener.IHttpProcessor;

import java.util.Map;

public class HttpManager implements IHttpProcessor {
    private static volatile HttpManager instance;
    private HttpManager() {
    }
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private static IHttpProcessor mHttpProcessor;

    public static void init(IHttpProcessor processor) {
        mHttpProcessor = processor;
    }

    @Override
    public void post(String url, Map<String, Object> map, Class<?> clazz) {
        mHttpProcessor.post(url, map, clazz);
    }

}
