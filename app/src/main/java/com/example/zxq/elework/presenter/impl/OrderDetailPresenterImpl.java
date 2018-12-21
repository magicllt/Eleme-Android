package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.OrderDetailDO;
import com.example.zxq.elework.model.OrderDetailModel;
import com.example.zxq.elework.model.impl.OrderDetailModelImpl;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.presenter.OrderDetailPresenter;
import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.OrderDetailView;

/**
 * Created by LLT on 2018/12/20.
 */

public class OrderDetailPresenterImpl implements OrderDetailPresenter {

    OrderDetailView orderDetailView;

    OrderDetailModel orderDetailModel;

    public OrderDetailPresenterImpl(OrderDetailView orderDetailView) {
        this.orderDetailView = orderDetailView;
        orderDetailModel = new OrderDetailModelImpl();
    }

    @Override
    public void getOrderDetail(int id) {
        orderDetailView.showLoadingView();
        orderDetailModel.getOrderDrtail(id, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                orderDetailView.showOrderDetailInfo((OrderDetailDO) (obj));
                orderDetailView.showNromalView();
            }

            @Override
            public void error(String msg) {
                orderDetailView.showMsg(msg);
                orderDetailView.showErrorView();
            }

            @Override
            public void noNet() {
                orderDetailView.showMsg(StringUtil.NO_NETWORK);
                orderDetailView.showErrorView();
            }
        });
    }
}
