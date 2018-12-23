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
 * OrderListModel的实现类
 */
public class OrderListModelImpl implements OrderListModel {

    /**
     * 获取更多的数据
     * @param id 用户id
     * @param page 页码
     * @param size 页大小
     * @param listener 监听器
     */
    @Override
    public void loadMoreDate(int id, int page, int size, final OnModelFinishedListener listener) {

        /// 构建请求，传入参数
        Request request = ItheimaHttp.newGetRequest(UrlUtil.getOrderList());
        request.putParams("id", id);
        request.putParams("pageNum", page);
        request.putParams("pageSize", size);

        /// 发送请求，传入监听器
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
