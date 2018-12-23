package com.example.zxq.elework.view.base;

import android.view.View;

/**
 * Created by LLT on 2018/12/16.
 * 管理 加载中，正常页面，加载失败 的接口
 */
public interface StateView {

    /**
     * @return 正常界面
     */
    View getNormalView();

    /**
     * @return 异常界面
     */
    View getErrorView();

    /**
     * @return 加载界面
     */
    View getLoadingView();

    /**
     * 隐藏全部
     */
    void hideAll();

    /**
     * 显示正常界面
     */
    void showNromalView();

    /**
     * 显示异常界面
     */
    void showErrorView();

    /**
     * 显示加载界面
     */
    void showLoadingView();

}
