package com.example.zxq.elework.view;

/**
 * Created by LLT on 2018/12/13.
 */

import com.example.zxq.elework.view.base.BaseView;

/**
 * 登录的view层
 */
public interface LoginView extends BaseView{

    /**
     * 清除输入框
     */
    void clearEdit();

    /**
     * 登录成功时候调用
     */
    void onloginSuccess();
}
