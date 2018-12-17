package com.example.zxq.elework.model.listener;

/**
 * Created by LLT on 2018/12/13.
 */

public interface OnModelFinishedListener{
    void success(Object obj);

    void error(String msg);

    void noNet();
}
