package com.example.zxq.elework.view.base;

import android.view.View;

/**
 * Created by LLT on 2018/12/16.
 */

public interface StateView {

    View getNormalView();

    View getErrorView();

    View getLoadingView();

    void hideAll();

    void showNromalView();

    void showErrorView();

    void showLoadingView();

}
