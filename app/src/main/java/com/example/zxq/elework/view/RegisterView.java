package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/16.
 */

public interface RegisterView extends BaseView {

    void clearEdit();

    void registerSuccess(UserDO userDO);
}
