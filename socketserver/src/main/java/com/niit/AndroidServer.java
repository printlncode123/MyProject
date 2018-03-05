package com.niit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroidServer {
    public static void main(String args[])throws IOException{
        ServerSocket server=new ServerSocket(30000);//服务端绑定3000端口
        System.out.print("server begin");//连接服务端成功在日志里打印此信息
        while(true){
            //等待客户端连接
            Socket socket=server.accept();//监听客户端传过来的消息
            new Thread(new AndroidRunnable(socket)).start();
        }
    }

}
