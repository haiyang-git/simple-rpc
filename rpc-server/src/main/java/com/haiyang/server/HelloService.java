package com.haiyang.server;

import com.haiyang.annotation.RemoteService;
import com.haiyang.api.IHelloService;

@RemoteService
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String s) {
        return "hello " + s;
    }
}
