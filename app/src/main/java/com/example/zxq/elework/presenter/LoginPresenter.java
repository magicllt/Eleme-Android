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
     * @param phone 手机号
     * @param pwd 密码
     */
    void login(String phone, String pwd);
}

