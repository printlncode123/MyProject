package com.software1411.niit.newapp.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.software1411.niit.newapp.newapp.config.SystemConfig;
import com.software1411.niit.newapp.newapp.util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.httpGetData_Get(SystemConfig.GET_CHANNEL);
    }
}