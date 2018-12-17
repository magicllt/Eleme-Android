package com.example.zxq.elework.view.base;

/**
 * Created by LLT on 2018/12/17.
 */

public interface LoadDataView {

    void finishLoadMore(boolean isSuccess);

    void finsihLoadMoreWithoutMoreData();

    void addData(Object object);
}
