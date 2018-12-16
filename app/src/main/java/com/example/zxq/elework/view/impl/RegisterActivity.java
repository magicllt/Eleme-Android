package com.example.zxq.elework.view.impl;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.presenter.RegisterPresenter;
import com.example.zxq.elework.presenter.impl.RegisterPresenterImpl;
import com.example.zxq.elework.view.RegisterView;
import com.example.zxq.elework.view.base.BaseActivity;

import java.net.Inet4Address;

public class RegisterActivity extends BaseActivity implements RegisterView {

    private EditText phoneEdit;
    private EditText pwdEdit;
    private EditText pwd2Edit;
    private Button registerBtn;
    private RegisterPresenter registerPresenter;
    private TextView clearText;

    static void actionStart(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initWidget();
        registerPresenter = new RegisterPresenterImpl(this);
    }

    private void initWidget() {
        phoneEdit = (EditText)findViewById(R.id.register_user_id);
        pwdEdit = (EditText)findViewById(R.id.register_user_pasaword);
        pwd2Edit = (EditText)findViewById(R.id.register_user_pasaword2);
        registerBtn = (Button)findViewById(R.id.register_register_btn);
        registerBtn.setOnClickListener(onClickListener);
        clearText = (TextView)findViewById(R.id.register_clear_text);
        clearText.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == registerBtn){
                String phone = phoneEdit.getText().toString();
                String pwd = pwdEdit.getText().toString();
                String pwd2 = pwd2Edit.getText().toString();
                registerPresenter.register(phone, pwd, pwd2);
                return;
            }else if (view == clearText){
                clearEdit();
                return;
            }
        }
    };

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEdit() {
        phoneEdit.setText("");
        pwdEdit.setText("");
        pwd2Edit.setText("");
    }

    @Override
    public void registerSuccess(UserDO userDO) {
        LoginActivity.ActionStart(this);
    }
}
