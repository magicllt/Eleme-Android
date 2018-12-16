package com.example.zxq.elework.model;

/**
 * Created by LLT on 2018/12/13.
 */

import com.example.zxq.elework.listener.OnModelFinishedListener;
import com.example.zxq.elework.presenter.LoginPresenter;

/**
 * 登录的model层
 */
public interface LoginModel {

    /**
     * 登录
     * @param phone
     * @param pwd
     */
    void login(String phone, String pwd, OnModelFinishedListener listener);

}
