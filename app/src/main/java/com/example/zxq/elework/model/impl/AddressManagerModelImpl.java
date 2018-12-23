package com.example.zxq.elework.model.impl;


import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
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
 * AddressManagerModel的实现类
 */

public class AddressManagerModelImpl implements AddressManagerModel {

    /**
     * 获取地址列表
     * @param listener 监听器
     */
    @Override
    public void listAddress(final OnModelFinishedListener listener) {
        /// 构建请求
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getAddressList());
        /// 发送请求，传入监听器
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

    /**
     *
     * @param id 地址的id
     * @param listener 监听器
     */
    @Override
    public void removeAddress(int id, final OnModelFinishedListener listener) {
        /// 构建请求，传入参数
        Request request = ItheimaHttp.newPostRequest(UrlUtil.getAddressDelete());
        request.putParams("id", id);
        /// 发送请求，传入监听器
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

}
