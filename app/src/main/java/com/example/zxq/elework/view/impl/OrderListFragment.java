package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.OrderListAdapter;
import com.example.zxq.elework.domain.OrderListItemBO;
import com.example.zxq.elework.presenter.OrderListPresenter;
import com.example.zxq.elework.presenter.impl.OrderListPresenterImpl;
import com.example.zxq.elework.view.OrderListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderListView的实现类
 */
public class OrderListFragment extends Fragment implements OrderListView{

    private View fragmentView;
    private Context context;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private OrderListPresenter orderListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_order_list, container, false);
        context = getActivity();
        initWidget();
        orderListPresenter = new OrderListPresenterImpl(this);
        orderListPresenter.loadMoreDate();
        return fragmentView;
    }

    /**
     * 初始化组件，设置事件监听器
     */
    private void initWidget() {
        refreshLayout = (SmartRefreshLayout)fragmentView.findViewById(R.id.order_history_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                orderListPresenter.loadMoreDate();
            }
        });

        recyclerView = (RecyclerView)fragmentView.findViewById(R.id.order_history_recycler_view);
        adapter = new OrderListAdapter(new ArrayList<OrderListItemBO>(), context, orderListListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 新建订单列表项的点击事件
     */
    OrderListAdapter.OrderListListener orderListListener = new OrderListAdapter.OrderListListener() {
        @Override
        public void onOrderItemClick(int id) {
            jumpToOrderDetail(id);
        }
    };

    /**
     * 展示消息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断加载是否成功
     * @param isSuccess 是否加载成功
     */
    @Override
    public void finishLoadMore(boolean isSuccess) {
        if (isSuccess){
            refreshLayout.finishLoadMore(2000);
            return;
        }else{
            refreshLayout.finishLoadMore(false);
            return;
        }
    }

    /**
     * 设置refreshLayout为没有更多数据可以加载
     */
    @Override
    public void finsihLoadMoreWithoutMoreData() {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    /**
     * 给适配器添加数据
     * @param object 添加的数据
     */
    @Override
    public void addData(Object object) {
        adapter.addData((List<OrderListItemBO>)(object));
    }

    /**
     * 跳转到订单详情
     * @param id 订单的id
     */
    @Override
    public void jumpToOrderDetail(int id) {
        OrderDetailActivity.actionStart(context, id);
    }
}
