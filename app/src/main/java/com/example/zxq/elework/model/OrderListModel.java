package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/20.
 * 订单列表的model
 */

public interface OrderListModel {
    /**
     * 加载更多的数据
     * @param id 用户id
     * @param page 页码
     * @param size 页大小
     * @param listener 监听器
     */
    void loadMoreDate(int id, int page, int size, OnModelFinishedListener listener);
}
