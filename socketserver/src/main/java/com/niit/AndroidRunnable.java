package com.niit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/9/26.
 */
public class AndroidRunnable implements Runnable{
    Socket socket=null;
    public AndroidRunnable(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        //向Android客户端输出hello client
        String line=null;
        InputStream is;//服务端的输入流
        OutputStream os;//服务端的输出流
        String str="hello client";//服务端要输出到客户端的字符串

        //向客户端发送信息
        try {
            os=socket.getOutputStream();
            is=socket.getInputStream();//接受客户端信息
            BufferedReader bff=new BufferedReader(new InputStreamReader(is));//将字节流转成字符流
            os.write(str.getBytes("utf-8"));//通过字节流写出
            os.flush();//将缓存中的数据强制写出
            socket.shutdownOutput();//半关闭socket
            //获取客户端信息
            while((line=bff.readLine())!=null){
                System.out.print(line);
            }
            //关闭输入输出流
            os.close();
            bff.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
