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
 * TakeAwayModel的实现类
 */
public class TakeAwayModelImpl implements TakeAwayModel {

    /**
     * 加载更多的数据
     * @param page 页号
     * @param size 页大小
     * @param listener 监听器
     */
    @Override
    public void loadMoreData(int page, int size, final OnModelFinishedListener listener) {
        /// 构建请求
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getShopList(page, size));
        /// 发送请求
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
