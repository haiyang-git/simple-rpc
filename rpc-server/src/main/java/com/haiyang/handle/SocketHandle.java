package com.haiyang.handle;

import com.haiyang.domain.RpcRequest;
import com.haiyang.util.MediatorMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandle implements Runnable {
    private Socket socket;

    public SocketHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream=new ObjectInputStream(socket.getInputStream());//?
            RpcRequest request=(RpcRequest)inputStream.readObject(); //反序列化
            //路由
            MediatorMap mediator=MediatorMap.getInstance();
            Object rs=mediator.process(request);
            System.out.println("服务端的执行结果："+rs);
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null!= inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!= outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
