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
 * OrderDetailModel的实现类
 */
public class OrderDetailModelImpl implements OrderDetailModel {

    /**
     * 获取订单详情
     * @param id 订单的id
     * @param listener 监听器
     */
    @Override
    public void getOrderDrtail(int id, final OnModelFinishedListener listener) {
        /// 构建请求
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getOrderDetail(id));
        /// 发起请求，传入监听器
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
