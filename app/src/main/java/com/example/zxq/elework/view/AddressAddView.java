package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/14.
 * 添加地址的view
 */
public interface AddressAddView extends BaseView{

    /**
     * 展示地址信息
     * @param addressDO 地址信息封装类
     */
    void showAddressInfo(AddressDO addressDO);

    /**
     * 点击先生按钮
     */
    void clickMaleBtn();

    /**
     * 点击女士按钮
     */
    void clickFemaleBtn();

    /**
     * 地址更新完毕调用的方法
     * @param addressDO 地址的信息
     */
    void updateAddressFinish(AddressDO addressDO);

    /**
     * 添加地址完毕调用的接口
     * @param addressDO 地址的信息
     */
    void addAddressFinish(AddressDO addressDO);

}
