package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.AddressAddModel;
import com.example.zxq.elework.model.impl.AddressAddModelImpl;
import com.example.zxq.elework.presenter.AddressAddPresenter;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.AddressAddView;

/**
 * Created by LLT on 2018/12/14.
 */

public class AddressAddPresenterImpl implements AddressAddPresenter {

    AddressAddView addressAddView;

    AddressAddModel addressAddModel;

    public AddressAddPresenterImpl(AddressAddView addressAddView){
        this.addressAddView = addressAddView;
        addressAddModel = new AddressAddModelImpl();
    }

    @Override
    public void saveAddress(final AddressDO addressDO) {
        addressDO.setUserId(MyApplication.getUser().getId());
        if (isDataOk(addressDO) == false){
            return;
        }
        addressAddModel.saveAddress(addressDO, new AbstractOnModelFinishedListener(addressAddView) {
            @Override
            public void success(Object obj) {
                addressAddView.addAddressFinish((AddressDO) (obj));
            }
        });
    }

    @Override
    public void updateAddress(final AddressDO addressDO) {
        if (isDataOk(addressDO) == false){
            return;
        }
        addressAddModel.updateAddress(addressDO, new AbstractOnModelFinishedListener(addressAddView) {
            @Override
            public void success(Object obj) {
                addressAddView.updateAddressFinish((AddressDO)(obj));
            }
        });
    }

    private boolean isDataOk(AddressDO addressDO) {
        if (ValidatorUtil.isEmpty(addressDO.getName()) == true){
            addressAddView.showMsg("请填写姓名");
            return false;
        }else if (ValidatorUtil.isEmpty(addressDO.getPhone()) == true || ValidatorUtil.isMobile(addressDO.getPhone()) == false){
            addressAddView.showMsg("请填写正确的手机号");
            return false;
        }else if (ValidatorUtil.isEmpty(addressDO.getAddress()) == true){
            addressAddView.showMsg("请填写收货地址");
            return false;
        }else if (ValidatorUtil.isEmpty(addressDO.getHouseNum()) == true){
            addressAddView.showMsg("请填写门牌号");
            return false;
        }
        return true;
    }
}
