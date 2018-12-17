package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.model.AddressAddModel;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.BeanUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by LLT on 2018/12/14.
 */

public class AddressAddModelImpl implements AddressAddModel {
    @Override
    public void saveAddress(final AddressDO addressDO, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newPostRequest(UrlUtil.getAddressSave());
        Map<String, Object>map = BeanUtil.object2MapWithoutNull(addressDO);
        request.putParamsMap(map);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<Integer>>(){
            @Override
            public void onResponse(Result<Integer> result) {
                if (result.getCode() == 0){
                    addressDO.setId(result.getData());
                    listener.success(addressDO);
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
    public void updateAddress(final AddressDO addressDO, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newPostRequest(UrlUtil.getAddressUpdate());
        Map<String, Object>map = BeanUtil.object2MapWithoutNull(addressDO);
        request.putParamsMap(map);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<Boolean>>() {
            @Override
            public void onResponse(Result<Boolean> result) {
                if (result.getCode() == 0){
                    listener.success(addressDO);
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
}
