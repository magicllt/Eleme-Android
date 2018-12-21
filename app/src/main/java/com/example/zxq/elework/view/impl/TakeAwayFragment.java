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


public class TakeAwayFragment extends Fragment implements TakeAwayView {

    private Context context;
    private View fragmentView;
    private ShopAdapter adapter;
    private SmartRefreshLayout refreshLayout;

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

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

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

    @Override
    public void finsihLoadMoreWithoutMoreData() {
        refreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void addData(Object object) {
        adapter.addData((List<ShopListItemDO>)(object));
    }

    @Override
    public void jumpToShopDetail(int id) {
        ShopMainActivity.actionStart(context, id);
    }
}
