package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/16.
 * 注册对应的view
 */
public interface RegisterView extends BaseView {

    /**
     * 清除输入款
     */
    void clearEdit();

    /**
     * 注册成功调用
     * @param userDO 用户的信息
     */
    void registerSuccess(UserDO userDO);
}
