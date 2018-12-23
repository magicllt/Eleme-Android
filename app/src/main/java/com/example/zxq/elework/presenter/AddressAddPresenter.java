package com.example.zxq.elework.presenter;

import com.example.zxq.elework.domain.AddressDO;

/**
 * Created by LLT on 2018/12/14.
 * 添加地址的presenter层
 */

public interface AddressAddPresenter {

    /**
     * 保存地址
     * @param addressDO 地址
     */
    void saveAddress(AddressDO addressDO);

    /**
     * 更新地址
     * @param addressDO 地址
     */
    void updateAddress(AddressDO addressDO);
}
