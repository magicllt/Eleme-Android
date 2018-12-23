package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/16.
 * 底部栏 我的分块 对于的view
 */

public interface MyTabView extends BaseView{
    /**
     * 展示用户信息
     * @param userDO 用户信息
     */
    void showUserInfo(UserDO userDO);
}
