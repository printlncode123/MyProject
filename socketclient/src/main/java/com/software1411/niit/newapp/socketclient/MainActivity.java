package com.software1411.niit.newapp.socketclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MainActivity extends AppCompatActivity {
    //定义变量
    EditText ed1;
    Button send;
    TextView txt1;
    Socket socket=null;
    InputStream is;
    OutputStream os;
    String buffer="";
    public Handler myHandler=new Handler(){
        public void handleMessage(Message message){
            if(message.what==0x11){
                Bundle bundle=message.getData();
                txt1.append("server:"+bundle.getString("message")+"\n");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取xml中的控件
        send= (Button) findViewById(R.id.send);
        txt1= (TextView) findViewById(R.id.txt1);
        ed1= (EditText) findViewById(R.id.ed1);

        //给按钮添加点击事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geted1=ed1.getText().toString();//获取编辑框发送的内容
                txt1.append("client:"+geted1+"\n");//将编辑框的数据追加到文本框中
                new MyThread(geted1).start();//启动线程socket必须在新线程中
            }
        });
    }
    class MyThread extends Thread{
        public String txt1;
        public MyThread(String str) {
            txt1=str;
        }
    }
    public void run(){
        //定义消息变量
        Message message=new Message();
        message.what=0x11;
        Bundle bundle=new Bundle();//Bundle相当于Map,Bundle用于activity之间传递数据，Bundle的putString(key,value)相当于Map的put(key,value)方法;putString(key,value)将数据存储在Bundle对象中，getString(key)根据key获取值
        bundle.clear();

        //连接服务器并设置连接超时为5秒
        try {
            socket = new Socket();

            socket.connect(new InetSocketAddress("10.0.2.2", 30000), 5000);//"10.0.2.2"虚拟地址
            //获取输入输出流
            os = socket.getOutputStream();
            BufferedReader bff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取发来服务器的信息
            String line = null;
            while ((line = bff.readLine()) != null) {
                buffer = line + buffer;
            }
            //向服务器发送信息
            os.write("android client".getBytes("utf-8"));
            os.flush();//将缓存中的数据强制写出
            bundle.putString("message", buffer.toString());
            message.setData(bundle);//将bundle保存在message里
            //发送消息，修改UI线程中的组件
            myHandler.handleMessage(message);
            //关闭各种输入输出流
            bff.close();
            os.close();
            socket.close();
        }catch (SocketTimeoutException aa){
            //连接超时 在UI界面显示消息
            bundle.putString("message","服务器连接失败!请检查网路是否打开");
            message.setData(bundle);
            //发送消息 修改UI线程中的组件
            myHandler.handleMessage(message);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /*   解析json数据：{"city":{"name":"beijing"},"weatherInfo":{"weather":"sunny"}}
        JSONObject jsonobject=new JSONObject(str);//创建json对象
        JSONObject jsonobject1 = jsonobject.getJSONObject("city");//获取city这个json对象
        JSONObject jsonobject2 = jsonobject.getJSONObject("weatherInfo");//获取weatherInfo这个json对象
        String name = jsonobject1.getString("city");//获取city这个json对象中的name元素的值
        String weather = jsonobject2.getString("weather");//获取weatherInfo这个json对象的weather元素的值*/

    }
