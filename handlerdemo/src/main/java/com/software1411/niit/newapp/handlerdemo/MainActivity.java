package com.software1411.niit.newapp.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int title=0;
    private Handler myHandler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    update();
                    break;
            }
        }
    };
    private void update(){
        setTitle("Welcome here"+title);
        ++title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer=new Timer();
        //设置计时器，2秒后启动计时器，每5秒执行一次任务
        timer.scheduleAtFixedRate(new mytask(), 2000, 5000);
    }

    public class mytask extends TimerTask {
        @Override
        public void run(){
            Message message=new Message();
            message.what=1;
            myHandler.sendMessage(message);
        }
    }
}
