package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.OrderDetailDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

/**
 * Created by LLT on 2018/12/20.
 * 订单详情对应的view
 */
public interface OrderDetailView extends BaseView, StateView{

    /**
     * 展示订单详情的信息
     * @param orderDetail 订单详情
     */
    void showOrderDetailInfo(OrderDetailDO orderDetail);
}
