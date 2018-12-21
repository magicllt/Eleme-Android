package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.model.OrderPayModel;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.HttpUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LLT on 2018/12/21.
 */

public class OrderPayModelImpl implements OrderPayModel {

    @Override
    public void submitOrder(OrderVo order, final OnModelFinishedListener listener) {
        HttpUtil.sendJsonRequest(UrlUtil.getOrderSave(), order, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.noNet();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Result<Boolean> result = new Gson().fromJson(response.body().string(), new TypeToken<Result<Boolean>>(){}.getType());
                if (result.getCode() == 0 && result.getData() == true){
                    listener.success(null);
                }else{
                    listener.error(result.getMsg());
                }
            }
        });
    }
}
