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
 * ShopMainPresenter的实现类
 */

public class ShopMainPresenterImpl implements ShopMainPresenter {

    ShopMainView shopMainView;

    ShopMainModel shopMainModel;

    public ShopMainPresenterImpl(ShopMainView shopMainView){
        this.shopMainView = shopMainView;
        shopMainModel = new ShopMainModelImpl();
    }

    /**
     * 获取商家的店铺信息和商品信息
     * @param id 商家id
     */
    @Override
    public void getShopAndGoods(int id) {
        /// 实现加载界面
        shopMainView.showLoadingView();
        /// 显示加载界面
        shopMainModel.getShopAndGoods(id, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                /// 加载成功，调用view.onGetShopAndGoodsSuccess()方法通知view数据获取成功
                shopMainView.onGetShopAndGoodsSuccess((ShopAndGoodsDO) (obj));
                shopMainView.showNromalView();
            }

            @Override
            public void error(String msg) {
                /// 调用view.showErrorView()通知view显示错误界面
                shopMainView.showErrorView();
            }

            @Override
            public void noNet() {
                /// 调用view.showErrorView()通知view显示错误界面
                shopMainView.showErrorView();
            }
        });
    }
}
