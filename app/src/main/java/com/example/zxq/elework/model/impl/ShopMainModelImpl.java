package com.example.zxq.elework.model.impl;

import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.model.ShopMainModel;
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
 * ShopMainModel的实现类
 */
public class ShopMainModelImpl implements ShopMainModel {

    /**
     * 获取店铺信息个商品信息
     * @param id 店铺id
     * @param listener 监听器
     */
    @Override
    public void getShopAndGoods(int id, final OnModelFinishedListener listener) {
        /// 构建请求
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getShopAndGoods(id));
        /// 发送请求
        Call call = ItheimaHttp.send(request, new HttpResponseListener<Result<ShopAndGoodsDO>>(){
            @Override
            public void onResponse(Result<ShopAndGoodsDO> result) {
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
