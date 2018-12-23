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
 * OrderListPresenter的实现类
 */
public class OrderListPresenterImpl implements OrderListPresenter {

    OrderListView orderListView;
    /// 维护下一次要请求的数据页码和数据页码大小
    private final PageInfoBreif info;
    private final OrderListModel orderListModel;

    public OrderListPresenterImpl(OrderListView orderListView){
        this.orderListView = orderListView;
        orderListModel = new OrderListModelImpl();
        /// 初始化pageInfoBrief(),总页码设为1，当前页码设为0，页大小设为5
        info = new PageInfoBreif();
        info.setPageNum(0);
        info.setPages(1);
        info.setPageSize(5);
    }

    /**
     * 加载更多数据
     */
    @Override
    public void loadMoreDate() {
        /// 当前页小于总页数，说明还有数据可以加载
        if (info.getPageNum() < info.getPages()){
            /// 传入用户ID，当前页数加一，页大小
            orderListModel.loadMoreDate(MyApplication.getUser().getId(), info.getPageNum() + 1, info.getPageSize(), new AbstractOnModelFinishedListener(orderListView) {
                @Override
                public void success(Object obj) {
                    /// 将获取到的数据发送给view，调用view的finsihLoadMore()提示加载成功.更新PageInfoBrief保存的当前页码和总页码
                    PageInfoBreif<OrderListItemBO>pageInfoBreif = (PageInfoBreif<OrderListItemBO>)(obj);
                    orderListView.addData(pageInfoBreif.getList());
                    info.setPageNum(pageInfoBreif.getPageNum());
                    info.setPages(pageInfoBreif.getPages());
                    orderListView.finishLoadMore(true);
                }
                @Override
                public void noNet() {
                    /// 调用父类方法，提示调用view的finishLoadMore(false)提示加载失败
                    super.noNet();
                    orderListView.finishLoadMore(false);
                }

                @Override
                public void error(String msg) {
                    /// 调用父类方法，提示调用view的finishLoadMore(false)提示加载失败
                    super.error(msg);
                    orderListView.finishLoadMore(false);
                }
            });
        }else{
            /// 调用orderListView,提示没有更多数据可以加载
            orderListView.finsihLoadMoreWithoutMoreData();
        }
    }
}
