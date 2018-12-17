package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

import java.util.List;

/**
 * Created by LLT on 2018/12/13.
 */

public interface AddressManagerView extends BaseView, StateView{

    void notifyAddData(AddressDO addressDO);

    void notifyRemoveData(int pos);

    void notifyUpdateData(AddressDO addressDO, int pos);

    void notifyListData(List<AddressDO> list);
}
