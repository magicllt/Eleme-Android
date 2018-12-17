package com.example.zxq.elework.view.base;

import android.view.View;

/**
 * Created by LLT on 2018/12/16.
 */

public abstract class AbstractStateActivity extends BaseActivity implements StateView {

    @Override
    abstract  public View getNormalView();

    @Override
    abstract  public View getErrorView();

    @Override
    abstract  public View getLoadingView();

    @Override
    public void hideAll() {
        getNormalView().setVisibility(View.INVISIBLE);
        getErrorView().setVisibility(View.INVISIBLE);
        getLoadingView().setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNromalView() {
        hideAll();
        getNormalView().setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        hideAll();
        getErrorView().setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingView() {
        hideAll();
        getLoadingView().setVisibility(View.VISIBLE);
    }
}
