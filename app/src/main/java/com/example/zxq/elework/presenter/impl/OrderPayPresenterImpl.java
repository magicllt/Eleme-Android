package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.model.OrderPayModel;
import com.example.zxq.elework.model.impl.OrderPayModelImpl;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.presenter.OrderPayPresenter;
import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.OrderPayView;

/**
 * Created by LLT on 2018/12/21.
 * OrderPayPresenter的实现类
 */

public class OrderPayPresenterImpl implements OrderPayPresenter {

    OrderPayModel orderPayModel;

    OrderPayView orderPayView;

    public OrderPayPresenterImpl(OrderPayView orderPayView) {
        this.orderPayView = orderPayView;
        orderPayModel = new OrderPayModelImpl();
    }

    /**
     * 提价订单
     * @param order 订单
     */
    @Override
    public void submitOrder(OrderVo order) {
        /// 调用model完成数据的提交
        orderPayModel.submitOrder(order, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                /// 提交成功，调用view的submitSuccess()
                orderPayView.submitSuccess();
            }

            @Override
            public void error(String msg) {
                /// 提交失败，调用view的submitFail(String msg)方法
                orderPayView.submitFail(msg);
            }

            @Override
            public void noNet() {
                /// 提交失败，调用view的submitFail(String msg)方法
                orderPayView.submitFail(StringUtil.NO_NETWORK);
            }
        });
    }
}
