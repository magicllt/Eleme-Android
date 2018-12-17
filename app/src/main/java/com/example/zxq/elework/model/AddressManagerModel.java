package com.example.zxq.elework.model;

import com.example.zxq.elework.model.listener.OnModelFinishedListener;

/**
 * Created by LLT on 2018/12/13.
 */

public interface AddressManagerModel {

    void listAddress(OnModelFinishedListener listener);

    void saveAddress();

    void removeAddress(int id, OnModelFinishedListener listener);

    void updateAddress();
}
