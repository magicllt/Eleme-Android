package com.example.zxq.elework.presenter;

/**
 * Created by LLT on 2018/12/13.
 */

import com.example.zxq.elework.view.LoginView;

/**
 * 登录的presenter层
 */
public interface LoginPresenter {

    /**
     * 登录
     * @param phone
     * @param pwd
     */
    void login(String phone, String pwd);
}

