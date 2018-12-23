package com.example.zxq.elework.model;

import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/21.
 * 订单支付的model
 */

public interface OrderPayModel {

    /**
     * 提交订单
     * @param order 订单
     * @param listener 监听器
     */
    void submitOrder(OrderVo order, OnModelFinishedListener listener);
}
