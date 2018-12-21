package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.domain.OrderListItemBO;
import com.example.zxq.elework.model.OrderListModel;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.result.PageInfoBreif;
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

public class OrderListModelImpl implements OrderListModel {
    @Override
    public void loadMoreDate(int id, int page, int size, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getOrderList());
        request.putParams("id", id);
        request.putParams("pageNum", page);
        request.putParams("pageSize", size);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<PageInfoBreif<OrderListItemBO>>>() {
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                super.onFailure(call, e);
                listener.noNet();
            }

            @Override
            public void onResponse(Result<PageInfoBreif<OrderListItemBO>> result) {
                if (result.getCode() == 0){
                    listener.success(result.getData());
                }else{
                    listener.error(result.getMsg());
                }
            }
        });
    }
}
