package com.example.zxq.elework.presenter;

import com.example.zxq.elework.view.RegisterView;

/**
 * Created by LLT on 2018/12/16.
 * 注册的presenter
 */

public interface RegisterPresenter {
    /**
     * 注册
     * @param phone 手机号
     * @param pwd 密码
     * @param pwd2 确认密码
     */
    void register(String phone, String pwd, String pwd2);
}
