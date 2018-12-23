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

/**
 * LoginView的实现类
 */
public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private EditText phoneEdit;
    private EditText pwdEdit;
    private Button loginBtn;
    LoginPresenter presenter;
    private TextView registerText;

    /**
     * 启动接口
     * @param context 上下文
     */
    static public void ActionStart(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /// 清空其他的全部活动
        ActivityController.finishOther(this);
        initWidget();
        presenter = new LoginPresenterImpl(this);
        /// 判断是否已经登录
        judgeLogined();
    }

    /**
     * 判断是否已经登录
     */
    private void judgeLogined() {
        /// 已经登录了，直接跳转到主界面
        if (MyApplication.getUser() != null){
            MainActivity.actionStart(this);
            this.finish();
        }
    }

    /**
     * 点击事件
     * 登录按钮：调用presenter.login()完成登录
     * 注册按钮：切换到注册界面
     */
    @Override
    public void onClick(View view) {
        if (view == loginBtn){
            presenter.login(phoneEdit.getText().toString(), pwdEdit.getText().toString());
            return;
        }else if (view == registerText){
            RegisterActivity.actionStart(this);
        }
    }

    /**
     * 展示信息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 情况输入框
     */
    @Override
    public void clearEdit() {
        phoneEdit.setText("");
        pwdEdit.setText("");
    }

    /**
     * 登录成功调用
     * 切换到主活动
     */
    @Override
    public void onloginSuccess(){
        MainActivity.actionStart(this);
    }

    /**
     * 初始化组件
     */
    private void initWidget() {
        phoneEdit = (EditText)findViewById(R.id.login_user_id);
        pwdEdit = (EditText)findViewById(R.id.login_user_pasaword);
        loginBtn = (Button)findViewById(R.id.login_login_btn);
        loginBtn.setOnClickListener(this);
        registerText = (TextView)findViewById(R.id.login_register_text);
        registerText.setOnClickListener(this);
    }
}
