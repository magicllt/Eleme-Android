package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.view.PaySuccessView;
import com.example.zxq.elework.view.base.BaseActivity;

/**
 * PaySuccessView的实现类
 */
public class PaySuccessActivity extends BaseActivity implements PaySuccessView {

    private Button returnMainBtn;

    /**
     * 活动的跳转接口
     * @param context 上下文
     */
    static public void actionStart(Context context){
        Intent intent = new Intent(context, PaySuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        initWidget();
    }

    /**
     * 初始化组件，设置监听器
     */
    private void initWidget() {
        returnMainBtn = (Button)findViewById(R.id.pay_success_return_main_btn);
        returnMainBtn.setOnClickListener(onClickListener);
    }

    /**
     * 点击事件的监听器
     * 1. returnMainBtn
     *      调用jumpToMain()方法跳转到MainActivity
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == returnMainBtn){
                jumpToMain();
            }
        }
    };

    /**
     * 显示信息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 切换到主活动
     */
    @Override
    public void jumpToMain() {
        MainActivity.actionStart(PaySuccessActivity.this);
        finish();
    }
}
