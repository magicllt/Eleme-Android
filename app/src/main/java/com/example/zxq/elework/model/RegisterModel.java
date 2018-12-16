package com.example.zxq.elework.model;

import com.example.zxq.elework.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/16.
 */

public interface RegisterModel {
    void register(String phone, String pwd, OnModelFinishedListener listener);
}
