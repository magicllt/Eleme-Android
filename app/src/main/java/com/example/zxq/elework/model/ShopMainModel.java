package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/20.
 * 商家主页的model
 */

public interface ShopMainModel {

    /**
     * 获取店铺信息和商品列表
     * @param id 店铺id
     * @param listener 监听器
     */
    void getShopAndGoods(int id, OnModelFinishedListener listener);
}
