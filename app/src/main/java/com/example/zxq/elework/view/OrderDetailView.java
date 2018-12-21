package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.OrderDetailDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

/**
 * Created by LLT on 2018/12/20.
 */

public interface OrderDetailView extends BaseView, StateView{

    void showOrderDetailInfo(OrderDetailDO orderDetail);
}
