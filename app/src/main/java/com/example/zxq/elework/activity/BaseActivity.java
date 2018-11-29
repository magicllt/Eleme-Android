package com.example.zxq.elework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxq.elework.application.MyApplication;

//所有活动的父类，添加一些共同的功能
public class BaseActivity extends AppCompatActivity {

//    标记当前活动
    public static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        // TODO: 2018/11/29 完整其他页面跳转登录界面的功能
    }
}
