package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.domain.OrderPayParam;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/21.
 */

public interface OrderPayView extends BaseView{

    void onAddressSelect(AddressDO addressDO);

    void submitSuccess();

    void submitFail(String msg);

    void showOrderInfo(OrderPayParam param);

    void showAddressInfo(AddressDO addressDO);
}
