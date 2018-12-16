package com.example.zxq.elework.listener;

import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/14.
 */

public abstract class AbstractOnModelFinishedListener implements OnModelFinishedListener {

    BaseView baseView;

    public AbstractOnModelFinishedListener(BaseView baseView){
        this.baseView = baseView;
    }

    @Override
    public void noNet() {
        baseView.showMsg(StringUtil.NO_NETWORK);
    }

    @Override
    public void error(String msg) {
        baseView.showMsg(msg);
    }
}
