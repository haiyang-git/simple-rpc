package com.haiyang.proxy;

import com.haiyang.domain.RpcRequest;
import com.haiyang.handle.SocketServerHandle;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
public class RemoteInvocationHandler implements InvocationHandler {
    private String host = "127.0.0.1";
    private int port = 9999;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SocketServerHandle socketServerHandle = new SocketServerHandle(host, port);
        RpcRequest request = new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypes(method.getParameterTypes()); //参数的类型
        request.setMethodName(method.getName());
        return socketServerHandle.send(request);
    }
}
