package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.domain.ShopListItemDO;
import com.example.zxq.elework.model.TakeAwayModel;
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
 * Created by LLT on 2018/12/17.
 */

public class TakeAwayModelImpl implements TakeAwayModel {

    @Override
    public void loadMoreData(int page, int size, final OnModelFinishedListener listener) {
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getShopList(page, size));
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<PageInfoBreif<ShopListItemDO>>>(){

            @Override
            public void onResponse(Result<PageInfoBreif<ShopListItemDO>> result) {
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
}
