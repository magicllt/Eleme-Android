package com.example.zxq.elework.view.base;

import android.view.View;

/**
 * Created by LLT on 2018/12/16.
 * 管理 加载中，正常页面，加载失败 的抽象类
 */

public abstract class AbstractStateActivity extends BaseActivity implements StateView {

    @Override
    abstract  public View getNormalView();

    @Override
    abstract  public View getErrorView();

    @Override
    abstract  public View getLoadingView();

    /**
     * 隐藏全部的活动
     */
    @Override
    public void hideAll() {
        getNormalView().setVisibility(View.INVISIBLE);
        getErrorView().setVisibility(View.INVISIBLE);
        getLoadingView().setVisibility(View.INVISIBLE);
    }

    /**
     * 展示正常界面
     */
    @Override
    public void showNromalView() {
        hideAll();
        getNormalView().setVisibility(View.VISIBLE);
    }

    /**
     * 展示异常界面
     */
    @Override
    public void showErrorView() {
        hideAll();
        getErrorView().setVisibility(View.VISIBLE);
    }

    /**
     * 展示加载界面
     */
    @Override
    public void showLoadingView() {
        hideAll();
        getLoadingView().setVisibility(View.VISIBLE);
    }
}
