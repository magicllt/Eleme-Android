package com.example.zxq.elework.view.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zxq.elework.controller.ActivityController;

/**
 * 顶层的活动父亲类
 * 功能包括: 添加活动到活动控制器，移除活动控制器中的活动，定义活动的TAG名
 */
public class BaseActivity extends AppCompatActivity {

    ///    标记当前活动
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /// 将活动的类名作为TAG的值， 添加活动到活动控制器
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        /// 将活动从活动控制器移除
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
