package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.model.ShopMainModel;
import com.example.zxq.elework.model.impl.ShopMainModelImpl;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.presenter.ShopMainPresenter;
import com.example.zxq.elework.view.ShopMainView;

/**
 * Created by LLT on 2018/12/20.
 */

public class ShopMainPresenterImpl implements ShopMainPresenter {

    ShopMainView shopMainView;

    ShopMainModel shopMainModel;

    public ShopMainPresenterImpl(ShopMainView shopMainView){
        this.shopMainView = shopMainView;
        shopMainModel = new ShopMainModelImpl();
    }

    @Override
    public void getShopAndGoods(int id) {
        shopMainView.showLoadingView();
        shopMainModel.getShopAndGoods(id, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                shopMainView.onGetShopAndGoodsSuccess((ShopAndGoodsDO) (obj));
                shopMainView.showNromalView();
            }

            @Override
            public void error(String msg) {
                shopMainView.showErrorView();
            }

            @Override
            public void noNet() {
                shopMainView.showErrorView();
            }
        });
    }
}
