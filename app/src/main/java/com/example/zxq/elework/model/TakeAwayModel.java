package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/17.
 */

public interface TakeAwayModel {

    void loadMoreData(int page, int size, OnModelFinishedListener listener);
}
