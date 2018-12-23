package com.example.zxq.elework.view;

import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.LoadDataView;

/**
 * Created by LLT on 2018/12/17.
 * 外卖对应的view
 */
public interface TakeAwayView extends LoadDataView, BaseView{

    /**
     * 跳转到商家详情
     * @param id 商家的id
     */
    void jumpToShopDetail(int id);
}
