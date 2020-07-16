package com.haiyang.handle;

import com.haiyang.domain.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketServerHandle {
    private String host;
    private int port;

    public SocketServerHandle(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest request) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            Socket socket = new Socket(host,port);
            //IO操作
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
             if(null!=inputStream){
                 try {
                     inputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(null!=outputStream){
                 try {
                     outputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        }
        return null;
    }
}
