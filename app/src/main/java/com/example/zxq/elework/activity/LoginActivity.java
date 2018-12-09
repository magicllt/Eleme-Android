package com.example.zxq.elework.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.activity.baseActivity.BaseActivity;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.HttpUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    Button loginBtn;

    EditText userPwdEdit;

    EditText userIdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        userIdEdit = (EditText)findViewById(R.id.user_id);
        userPwdEdit = (EditText)findViewById(R.id.user_pasaword);
        loginBtn =  (Button)findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(myOnClickListener);
    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == loginBtn){
                login();
            }
        }
    };

    private void login(){
        String phoneStr = userIdEdit.getText().toString();
        String pwdStr = userPwdEdit.getText().toString();

        if (isDataValid(phoneStr, pwdStr)){
//                2. 发送网络请求
//                构建requestBody
            RequestBody body = new FormBody.Builder()
                    .add("uPhone", phoneStr)
                    .add("uPassword", pwdStr)
                    .build();
//                发送请求
            Log.d(TAG, UrlUtil.getUserLoginUrl());
            HttpUtil.sendOkHttpPostRequest(UrlUtil.getUserLoginUrl(), body, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            HttpUtil.accessServerFail();
                        }
                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //响应成功
                    if (response.isSuccessful()){
                        String responseData = response.body().string();
                        Gson gson = new Gson();
                        final Result<UserDO> result = gson.fromJson(responseData, new TypeToken<Result<UserDO>>(){}.getType());
                        if (result.getCode() == 0){
                            UserDO user = result.getData();
                            MyApplication.setUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    HttpUtil.ResultFail(result);
                                }
                            });
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HttpUtil.accessServerFail();
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 判断用户输入的手机号码和密码是否符合基本的规范
     * @param phoneStr 手机号码字符串
     * @param pwdStr 密码字符串
     * @return 判断数据是否符合规范
     */
    private boolean isDataValid(String phoneStr, String pwdStr){
//        数据非空检查
        if (ValidatorUtil.isEmpty(phoneStr) == false && ValidatorUtil.isEmpty(pwdStr) == false) {
//            手机号码检查
            if (ValidatorUtil.isMobile(phoneStr)){
                return true;
            }else{
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "手机号码和密码不能为空", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
