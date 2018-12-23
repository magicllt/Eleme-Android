package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/13.
 * 地址管理的model
 */
public interface AddressManagerModel {

    /**
     * 获取地址列表
     * @param listener 监听器
     */
    void listAddress(OnModelFinishedListener listener);


    /**
     * 移除地址
     * @param id 地址的id
     * @param listener 监听器
     */
    void removeAddress(int id, OnModelFinishedListener listener);

}
