package com.example.zxq.elework.model.listener;

import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/14.
 * 实现了OnModelFinishListener两个方法的抽象监听器
 */

public abstract class AbstractOnModelFinishedListener implements OnModelFinishedListener {

    /**
     * View层的顶级父类接口
     */
    BaseView baseView;

    /***
     * 初始化，完成BaseView的绑定
     * @param baseView view层的接口
     */
    public AbstractOnModelFinishedListener(BaseView baseView){
        this.baseView = baseView;
    }

    /**
     * 网络异常的时候调用
     * 调用BaseView.showMsg(String msg)打印网络异常信息
     */
    @Override
    public void noNet() {
        baseView.showMsg(StringUtil.NO_NETWORK);
    }


    /**
     * 任务执行异常时候调用
     * 调用BaseView.showMsg(String msg)打印任务执行失败的信息
     */
    @Override
    public void error(String msg) {
        baseView.showMsg(msg);
    }
}
