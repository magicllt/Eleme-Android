package com.example.zxq.elework.presenter;

import com.example.zxq.elework.domain.OrderVo;

/**
 * Created by LLT on 2018/12/21.
 * 订单支付的presenter
 */

public interface OrderPayPresenter {

    /**
     * 提交订单
     * @param order 订单
     */
    void submitOrder(OrderVo order);
}
