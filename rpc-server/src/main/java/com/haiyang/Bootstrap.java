package com.haiyang;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan({"com.haiyang.annotation","com.haiyang.domain","com.haiyang.handle","com.haiyang.init","com.haiyang.server","com.haiyang.util"})
@ComponentScan("com.haiyang")
public class Bootstrap {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Bootstrap.class);
        System.out.println(ac);
    }
}

