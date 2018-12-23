package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/20.
 * 订单详情的model
 */

public interface OrderDetailModel {

    /**
     * 获取订单详情
     * @param id 订单的id
     * @param listener 监听器
     */
    void getOrderDrtail(int id, OnModelFinishedListener listener);
}
