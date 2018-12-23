package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/16.
 * 注册的model
 */

public interface RegisterModel {
    /**
     * 注册
     * @param phone 手机号
     * @param pwd 密码
     * @param listener 监听器
     */
    void register(String phone, String pwd, OnModelFinishedListener listener);
}
