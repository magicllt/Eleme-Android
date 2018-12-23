package com.example.zxq.elework.model;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/14.
 * 添加地址的model
 */

public interface AddressAddModel {

    /**
     * 保存地址
     * @param addressDO 地址
     * @param listener 监听器
     */
    void saveAddress(AddressDO addressDO, OnModelFinishedListener listener);

    /**
     * 更新地址
     * @param addressDO 地址
     * @param listener 监听器
     */
    void updateAddress(AddressDO addressDO, OnModelFinishedListener listener);

}
