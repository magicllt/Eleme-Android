package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/20.
 */

public interface ShopMainModel {

    void getShopAndGoods(int id, OnModelFinishedListener listener);
}
