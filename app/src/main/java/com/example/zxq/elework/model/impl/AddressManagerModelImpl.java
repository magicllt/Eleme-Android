package com.example.zxq.elework.model.impl;


import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.listener.OnModelFinishedListener;
import com.example.zxq.elework.model.AddressManagerModel;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.UrlUtil;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.List;

/**
 * Created by LLT on 2018/12/13.
 */

public class AddressManagerModelImpl implements AddressManagerModel {

    @Override
    public void listAddress(final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getAddressList());
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<List<AddressDO>>>() {
            @Override
            public void onResponse(Result<List<AddressDO>> result) {
                if (result.getCode() == 0){
                    listener.success(result.getData());
                }else{
                    listener.error(result.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                super.onFailure(call, e);
                listener.noNet();
            }
        });
    }

    @Override
    public void saveAddress() {

    }

    @Override
    public void removeAddress(int id, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newPostRequest(UrlUtil.getAddressDelete());
        request.putParams("id", id);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<Boolean>>() {
            @Override
            public void onResponse(Result<Boolean> result) {
                if (result.getCode() == 0 && result.getData() == true){
                    listener.success(null);
                }else{
                    listener.error(result.getMsg());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                super.onFailure(call, e);
                listener.noNet();
            }
        });
    }

    @Override
    public void updateAddress() {

    }
}
