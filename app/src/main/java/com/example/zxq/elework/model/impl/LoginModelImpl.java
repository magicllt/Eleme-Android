package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.model.LoginModel;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.UrlUtil;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;


import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by LLT on 2018/12/13.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(String phone, String pwd, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newPostRequest(UrlUtil.getUserLoginUrl());
        request.putParams("uPhone", phone);
        request.putParams("uPassword", pwd);

        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<UserDO>>() {
            @Override
            public void onResponse(Result<UserDO> result) {
                if (result.getCode() == 0){
                    listener.success(null);
                    MyApplication.setUser(result.getData());
                }else{
                    listener.error(result.getMsg());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable e) {
                super.onFailure(call, e);
                listener.noNet();
            }
        });
    }
}
