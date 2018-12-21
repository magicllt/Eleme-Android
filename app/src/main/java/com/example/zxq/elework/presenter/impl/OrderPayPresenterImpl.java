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
 */

public class OrderPayPresenterImpl implements OrderPayPresenter {

    OrderPayModel orderPayModel;

    OrderPayView orderPayView;

    public OrderPayPresenterImpl(OrderPayView orderPayView) {
        this.orderPayView = orderPayView;
        orderPayModel = new OrderPayModelImpl();
    }

    @Override
    public void submitOrder(OrderVo order) {
        orderPayModel.submitOrder(order, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                orderPayView.submitSuccess();
            }

            @Override
            public void error(String msg) {
                orderPayView.submitFail(msg);
            }

            @Override
            public void noNet() {
                orderPayView.submitFail(StringUtil.NO_NETWORK);
            }
        });
    }
}
