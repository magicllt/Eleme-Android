package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.OrderListItemBO;
import com.example.zxq.elework.model.OrderListModel;
import com.example.zxq.elework.model.impl.OrderListModelImpl;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.presenter.OrderListPresenter;
import com.example.zxq.elework.result.PageInfoBreif;
import com.example.zxq.elework.view.OrderListView;

/**
 * Created by LLT on 2018/12/20.
 */

public class OrderListPresenterImpl implements OrderListPresenter {

    OrderListView orderListView;
    private final PageInfoBreif info;
    private final OrderListModel orderListModel;


    public OrderListPresenterImpl(OrderListView orderListView){
        this.orderListView = orderListView;
        orderListModel = new OrderListModelImpl();
        info = new PageInfoBreif();
        info.setPageNum(0);
        info.setPages(1);
        info.setPageSize(5);
    }

    @Override
    public void loadMoreDate() {
        if (info.getPageNum() < info.getPages()){
            orderListModel.loadMoreDate(MyApplication.getUser().getId(), info.getPageNum() + 1, info.getPageSize(), new AbstractOnModelFinishedListener(orderListView) {
                @Override
                public void success(Object obj) {
                    PageInfoBreif<OrderListItemBO>pageInfoBreif = (PageInfoBreif<OrderListItemBO>)(obj);
                    orderListView.addData(pageInfoBreif.getList());
                    info.setPageNum(pageInfoBreif.getPageNum());
                    info.setPages(pageInfoBreif.getPages());
                    orderListView.finishLoadMore(true);
                }
                @Override
                public void noNet() {
                    super.noNet();
                    orderListView.finishLoadMore(false);
                }

                @Override
                public void error(String msg) {
                    super.error(msg);
                    orderListView.finishLoadMore(false);
                }
            });
        }else{
            orderListView.finsihLoadMoreWithoutMoreData();
        }
    }
}
