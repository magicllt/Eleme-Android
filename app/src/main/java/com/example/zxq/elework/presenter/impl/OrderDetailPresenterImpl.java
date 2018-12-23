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
 * OrderDetailPresenter的实现类
 */
public class OrderDetailPresenterImpl implements OrderDetailPresenter {

    OrderDetailView orderDetailView;

    OrderDetailModel orderDetailModel;

    public OrderDetailPresenterImpl(OrderDetailView orderDetailView) {
        this.orderDetailView = orderDetailView;
        orderDetailModel = new OrderDetailModelImpl();
    }

    /**
     * 获取订单的详情
     * @param id 订单的ID
     */
    @Override
    public void getOrderDetail(int id) {
        /// 显示加载状态
        orderDetailView.showLoadingView();
        /// 调用model获取订单详情
        orderDetailModel.getOrderDrtail(id, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                /// 调用view的showOrderDetail()方法将数据渲染到视图，显示正常的页面
                orderDetailView.showOrderDetailInfo((OrderDetailDO) (obj));
                orderDetailView.showNromalView();
            }

            @Override
            public void error(String msg) {
                /// 打印错误信息，实现错误的页面
                orderDetailView.showMsg(msg);
                orderDetailView.showErrorView();
            }

            @Override
            public void noNet() {
                /// 打印网路异常的信息，切切换到网络异常的界面
                orderDetailView.showMsg(StringUtil.NO_NETWORK);
                orderDetailView.showErrorView();
            }
        });
    }
}
