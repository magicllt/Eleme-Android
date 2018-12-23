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
import com.example.zxq.elework.adapter.ShopGoodsListAdapter;
import com.example.zxq.elework.view.ShopGoodsListView;

/**
 * ShopGoodsListView的实现类
 */
public class ShopGoodsListFragment extends Fragment implements ShopGoodsListView{

    private View fragmentView;
    private Context context;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_shop_goods_list, container, false);
        context = getActivity();
        initWidget();
        return fragmentView;
    }

    /**
     * 初始化界面，完成事件绑定
     */
    private void initWidget() {
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.fragment_shop_goods_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        ShopMainActivity activity = (ShopMainActivity)(getActivity());
        ShopGoodsListAdapter adapter = new ShopGoodsListAdapter(activity.getGoodsList(), activity.getGoodsNumList(), activity, activity.getOnGoodsNumChangeListener());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
