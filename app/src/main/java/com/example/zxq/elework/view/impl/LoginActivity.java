package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.controller.ActivityController;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.presenter.LoginPresenter;
import com.example.zxq.elework.presenter.impl.LoginPresenterImpl;
import com.example.zxq.elework.view.LoginView;
import com.example.zxq.elework.view.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private EditText phoneEdit;
    private EditText pwdEdit;
    private Button loginBtn;
    LoginPresenter presenter;
    private TextView registerText;

    static public void ActionStart(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //清空其他的全部活动
        ActivityController.finishOther(this);
        initWidget();
        presenter = new LoginPresenterImpl(this);
        //判断是否已经登录
        judgeLogined();
    }

    private void judgeLogined() {
        if (MyApplication.getUser() != null){
            MainActivity.actionStart(this);
            this.finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == loginBtn){
            presenter.login(phoneEdit.getText().toString(), pwdEdit.getText().toString());
            return;
        }else if (view == registerText){
            RegisterActivity.actionStart(this);
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEdit() {
        phoneEdit.setText("");
        pwdEdit.setText("");
    }

    @Override
    public void onloginSuccess(){

        MainActivity.actionStart(this);
    }

    private void initWidget() {
        phoneEdit = (EditText)findViewById(R.id.login_user_id);
        pwdEdit = (EditText)findViewById(R.id.login_user_pasaword);
        loginBtn = (Button)findViewById(R.id.login_login_btn);
        loginBtn.setOnClickListener(this);
        registerText = (TextView)findViewById(R.id.login_register_text);
        registerText.setOnClickListener(this);
    }
}
