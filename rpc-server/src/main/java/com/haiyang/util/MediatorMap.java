package com.haiyang.util;


import com.haiyang.domain.BeanMethod;
import com.haiyang.domain.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MediatorMap {
    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();
    public static volatile MediatorMap mediatorMap;

    private MediatorMap() {
    }

    public static MediatorMap getInstance() {
        if (null == mediatorMap) {
            synchronized (MediatorMap.class) {
                if (null == mediatorMap) {
                    mediatorMap = new MediatorMap();
                }
            }
        }
        return mediatorMap;
    }

    public Object process(RpcRequest request) {
        String key = request.getClassName() + "." + request.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (null == beanMethod) {
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean, request.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
