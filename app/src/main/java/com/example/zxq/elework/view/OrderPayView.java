package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.domain.OrderPayParam;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/21.
 * 订单支付对应的view
 */
public interface OrderPayView extends BaseView{

    /**
     * 地址选中调用的方法
     * @param addressDO 选中的地址信息
     */
    void onAddressSelect(AddressDO addressDO);

    /**
     * 提交成功调用
     */
    void submitSuccess();

    /**
     * 提交失败调用
     * @param msg
     */
    void submitFail(String msg);

    /**
     * 展示订单信息
     * @param param 订单的信息
     */
    void showOrderInfo(OrderPayParam param);

    /**
     * 展示地址的信息
     * @param addressDO 地址的信息
     */
    void showAddressInfo(AddressDO addressDO);
}
