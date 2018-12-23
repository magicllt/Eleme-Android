package com.example.zxq.elework.view.base;

/**
 * Created by LLT on 2018/12/17.
 * 分页加载对应的接口
 */
public interface LoadDataView {

    /**
     * 加载更多的回调接口
     * @param isSuccess 是否加载成功
     */
    void finishLoadMore(boolean isSuccess);

    /**
     * 没有更多数据可以记载
     */
    void finsihLoadMoreWithoutMoreData();

    /**
     * 添加数据
     * @param object 添加的数据
     */
    void addData(Object object);
}
