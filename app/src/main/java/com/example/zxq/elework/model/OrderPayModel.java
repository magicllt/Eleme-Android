package com.example.zxq.elework.model;

import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/21.
 */

public interface OrderPayModel {

    void submitOrder(OrderVo order, OnModelFinishedListener listener);
}
