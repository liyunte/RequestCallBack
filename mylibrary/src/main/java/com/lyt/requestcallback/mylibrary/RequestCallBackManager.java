package com.lyt.requestcallback.mylibrary;

import android.support.annotation.NonNull;

import com.lyt.requestcallback.mylibrary.annotation.RequestCallBack;
import com.lyt.requestcallback.mylibrary.bean.MethodBean;
import com.lyt.requestcallback.mylibrary.type.Response;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 网络请求回调
 */
public class RequestCallBackManager {
    private Map<Object, List<MethodBean>> networkList;

    private RequestCallBackManager() {
        networkList = new HashMap<>();
    }

    private static volatile RequestCallBackManager instance;
    public static RequestCallBackManager getInstance(){
        if (instance==null){
            synchronized (RequestCallBackManager.class){
                if (instance==null){
                    instance = new RequestCallBackManager();
                }
            }
        }
        return instance;
    }

    public void register(@NonNull Object register){
       if (register==null){
           throw new NullPointerException("注册的对象不能为空");
       }
        List<MethodBean> methodList = networkList.get(register);
        if (methodList == null) {
            methodList = findAnnotationMethod(register);
            networkList.put(register, methodList);
        }

    }
    public void unRegister(Object register){
        if (register==null){
            throw new NullPointerException("注册的对象不能为空");
        }
        if (!networkList.isEmpty()) {
            networkList.remove(register);
        }
    }
    public void unRegisterAll(){
        if (!networkList.isEmpty()){
            networkList.clear();
        }
    }

    /**
     * 网络事件分发
     *
     * @param response
     */
    public void post(Response response) {
        if (response!=null){
            Set<Object> set = networkList.keySet();
            for (Object getter : set) {
                List<MethodBean> methodList = networkList.get(getter);
                if (methodList != null) {
                    for (MethodBean method : methodList) {
                        if (method.getType().isAssignableFrom(response.getClass())) {
                          if (method.getRequestType().isEmpty()){
                              invoke(method,getter,response);
                          }else if (method.getRequestType().equalsIgnoreCase(response.getRequestType())){
                              invoke(method,getter,response);
                          }
                        }
                    }
                }
            }
        }

    }
    /**
     * 查找register中所有使用@Network注解的方法，封装成MethodManager并保存到集合中
     *
     * @param register
     * @return
     */
    private List<MethodBean> findAnnotationMethod(Object register) {
        List<MethodBean> methodList = new ArrayList<>();
        Class<?> clazz = register.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            RequestCallBack callBack = method.getAnnotation(RequestCallBack.class);
            if (callBack == null) {
                continue;
            }
            //方法注解的校验
            Type returnType = method.getGenericReturnType();
            if (!"void".equalsIgnoreCase(returnType.toString())) {
                throw new RuntimeException("方法返回不是void");
            }

            Class<?>[] parmeterTypes = method.getParameterTypes();
            //方法返回类型检查
            if (parmeterTypes.length != 1) {
                throw new RuntimeException(method.getName() + "方法有且只有一个");
            }
            MethodBean methodManager = new MethodBean(parmeterTypes[0], callBack.requestType(), method);
            methodList.add(methodManager);

        }
        return methodList;
    }

    /**
     * 反射执行方法
     *
     * @param method
     * @param getter
     * @param response
     */
    private void invoke(MethodBean method, Object getter, Response response) {
        try {
            method.getMethod().invoke(getter, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
