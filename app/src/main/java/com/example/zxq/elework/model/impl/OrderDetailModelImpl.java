package com.example.zxq.elework.model.impl;


import com.example.zxq.elework.domain.OrderDetailDO;
import com.example.zxq.elework.model.OrderDetailModel;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.UrlUtil;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by LLT on 2018/12/20.
 */

public class OrderDetailModelImpl implements OrderDetailModel {
    
    @Override
    public void getOrderDrtail(int id, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getOrderDetail(id));
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<OrderDetailDO>>() {
            @Override
            public void onResponse(Result<OrderDetailDO> result) {
                if (result.getCode() == 0){
                    listener.success(result.getData());
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
