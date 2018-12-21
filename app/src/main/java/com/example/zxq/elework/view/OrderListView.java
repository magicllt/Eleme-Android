package com.example.zxq.elework.view;

import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.LoadDataView;

/**
 * Created by LLT on 2018/12/19.
 */

public interface OrderListView extends LoadDataView, BaseView{
    
    void jumpToOrderDetail(int id);
}
