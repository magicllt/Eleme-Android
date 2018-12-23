package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.ShopAdapter;
import com.example.zxq.elework.domain.ShopListItemDO;
import com.example.zxq.elework.presenter.TakeAwayPresenter;
import com.example.zxq.elework.presenter.impl.TakeAwayPresenterImpl;
import com.example.zxq.elework.view.TakeAwayView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * TakeAwayView的实现类
 */
public class TakeAwayFragment extends Fragment implements TakeAwayView {

    private Context context;
    private View fragmentView;
    private ShopAdapter adapter;
    private SmartRefreshLayout refreshLayout;

    /// 商家列表项的点击事件
    private ShopAdapter.ShopAdapterListener shopAdapterListener = new ShopAdapter.ShopAdapterListener() {
        @Override
        public void onShopListItemClick(int id) {
            jumpToShopDetail(id);
        }
    };
    private TakeAwayPresenter takeAwayPresenter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_take_away, container, false);
        context = getActivity();
        initWidget();
        takeAwayPresenter = new TakeAwayPresenterImpl(this);
        takeAwayPresenter.loadMoreData();
        return fragmentView;
    }

    /**
     * 初始化组件并且设置监听器
     */
    private void initWidget() {
        refreshLayout = (SmartRefreshLayout)fragmentView.findViewById(R.id.take_out_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                //refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                takeAwayPresenter.loadMoreData();
            }
        });

        recyclerView = (RecyclerView)fragmentView.findViewById(R.id.take_out_recycler_view);
        adapter = new ShopAdapter(new ArrayList<ShopListItemDO>(), context, shopAdapterListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 展示信息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载数据的结束的调用方法
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
     * 没有更多数据的调用方法
     */
    @Override
    public void finsihLoadMoreWithoutMoreData() {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    /**
     * 添加数据
     * @param object 添加的数据
     */
    @Override
    public void addData(Object object) {
        adapter.addData((List<ShopListItemDO>)(object));
    }

    /**
     * 切换到订单详情
     * @param id 商家的id
     */
    @Override
    public void jumpToShopDetail(int id) {
        ShopMainActivity.actionStart(context, id);
    }
}
