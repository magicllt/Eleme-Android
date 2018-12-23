package com.example.zxq.elework.presenter;

/**
 * Created by LLT on 2018/12/13.
 * 地址管理的presenter
 */

public interface AddressManagerPresenter {

    /**
     * 获取地址列表
     */
    void listAddress();


    /**
     * 移除地址
     * @param pos 适配器的下标
     * @param id 要移除的地址的id
     */
    void removeAddress(int pos, int id);

}
