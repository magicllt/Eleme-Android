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

public class PaySuccessActivity extends BaseActivity implements PaySuccessView {

    private Button returnMainBtn;

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

    private void initWidget() {
        returnMainBtn = (Button)findViewById(R.id.pay_success_return_main_btn);
        returnMainBtn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == returnMainBtn){
                jumpToMain();
            }
        }
    };

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void jumpToMain() {
        MainActivity.actionStart(PaySuccessActivity.this);
        finish();
    }
}
