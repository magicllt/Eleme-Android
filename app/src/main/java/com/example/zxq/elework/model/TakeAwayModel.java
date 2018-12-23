package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/17.
 * 外卖的model
 */

public interface TakeAwayModel {

    /**
     * 加载更多的店铺列表细腻
     * @param page 页号
     * @param size 页大小
     * @param listener 监听器
     */
    void loadMoreData(int page, int size, OnModelFinishedListener listener);
}
