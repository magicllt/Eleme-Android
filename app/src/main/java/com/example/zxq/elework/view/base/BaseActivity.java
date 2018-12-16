package com.example.zxq.elework.view.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//所有活动的父类，添加一些共同的功能
public class BaseActivity extends AppCompatActivity {

    //    标记当前活动
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        //取消顶部条
        getSupportActionBar().hide();

    }
}
