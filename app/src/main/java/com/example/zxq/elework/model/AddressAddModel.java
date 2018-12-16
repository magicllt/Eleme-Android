package com.example.zxq.elework.model;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/14.
 */

public interface AddressAddModel {

    void saveAddress(AddressDO addressDO, OnModelFinishedListener listener);

    void updateAddress(AddressDO addressDO, OnModelFinishedListener listener);

}
