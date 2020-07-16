package com.haiyang.init;

import com.haiyang.annotation.RemoteService;
import com.haiyang.domain.BeanMethod;
import com.haiyang.util.MediatorMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class InitMediatorMap implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().isAnnotationPresent(RemoteService.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String key=bean.getClass().getInterfaces()[0].getName()+"."+method.getName();
                BeanMethod beanMethod=new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                MediatorMap.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
