package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/14.
 */

public interface AddressAddView extends BaseView{

    void showAddressInfo(AddressDO addressDO);

    void clickMaleBtn();

    void clickFemaleBtn();

    void updateAddressFinish(AddressDO addressDO);

    void addAddressFinish(AddressDO addressDO);

}
