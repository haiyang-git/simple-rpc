package com.haiyang.init;

import com.haiyang.handle.SocketHandle;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Spring 容器启动之后 会发布一个事件ContextRefreshedEvent
 */
@Component
public class InitSocketServer implements  ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动服务
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                //监听客户端请求
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketHandle(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
