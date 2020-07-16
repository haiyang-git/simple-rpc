package com.haiyang.controller;

import com.haiyang.annotation.Reference;
import com.haiyang.api.IHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    private IHelloService service;


    @GetMapping("/say")
    public String say(){
        return service.sayHello("靓仔");
    }




}