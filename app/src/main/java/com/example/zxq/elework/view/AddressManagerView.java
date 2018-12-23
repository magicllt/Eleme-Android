package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

import java.util.List;

/**
 * Created by LLT on 2018/12/13.
 * 管理地址的view
 */

public interface AddressManagerView extends BaseView, StateView{

    /**
     * 添加地址成功调用的接口
     * @param addressDO 地址的信息
     */
    void notifyAddData(AddressDO addressDO);

    /**
     * 移除地址调用的接口
     * @param pos 移除的地址列表项的下标
     */
    void notifyRemoveData(int pos);

    /**
     * 更新地址调用的接口
     * @param addressDO 更新的地址信息
     * @param pos 更新的地址列表项的下标
     */
    void notifyUpdateData(AddressDO addressDO, int pos);

    /**
     * 获取地址列表成功调用的放阿飞
     * @param list 获取到的地址列表
     */
    void notifyListData(List<AddressDO> list);
}
