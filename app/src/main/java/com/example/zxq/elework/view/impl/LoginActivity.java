package com.example.zxq.elework.view.impl;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.presenter.LoginPresenter;
import com.example.zxq.elework.presenter.impl.LoginPresenterImpl;
import com.example.zxq.elework.view.LoginView;
import com.example.zxq.elework.view.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private EditText phoneEdit;
    private EditText pwdEdit;
    private Button loginBtn;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        if (view == loginBtn){
            presenter.login(phoneEdit.getText().toString(), pwdEdit.getText().toString());
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

    private void initWidget() {
        phoneEdit = (EditText)findViewById(R.id.login_user_id);
        pwdEdit = (EditText)findViewById(R.id.login_user_pasaword);
        loginBtn = (Button)findViewById(R.id.login_login_btn);
        loginBtn.setOnClickListener(this);
    }
}
