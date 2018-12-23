package com.example.zxq.elework.view;

import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.LoadDataView;

/**
 * Created by LLT on 2018/12/19.
 * 订单列表项对应的view
 */
public interface OrderListView extends LoadDataView, BaseView{

    /**
     * 跳转到订单详情
     * @param id 订单的id
     */
    void jumpToOrderDetail(int id);
}
