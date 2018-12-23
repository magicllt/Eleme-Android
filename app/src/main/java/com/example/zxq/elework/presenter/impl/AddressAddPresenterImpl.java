package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.AddressAddModel;
import com.example.zxq.elework.model.impl.AddressAddModelImpl;
import com.example.zxq.elework.presenter.AddressAddPresenter;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.AddressAddView;

/**
 * Created by LLT on 2018/12/14.
 * AddressAddPresenter的实现类
 */

public class AddressAddPresenterImpl implements AddressAddPresenter {

    AddressAddView addressAddView;

    AddressAddModel addressAddModel;

    public AddressAddPresenterImpl(AddressAddView addressAddView){
        this.addressAddView = addressAddView;
        addressAddModel = new AddressAddModelImpl();
    }

    /**
     * 保存地址
     * @param addressDO 地址
     */
    @Override
    public void saveAddress(final AddressDO addressDO) {
        /// 获取application中保存的的用户id
        addressDO.setUserId(MyApplication.getUser().getId());
        /// 检验地址的数据是否合法
        if (isDataOk(addressDO) == false){
            return;
        }
        /// 调用model将数据保存到服务器
        addressAddModel.saveAddress(addressDO, new AbstractOnModelFinishedListener(addressAddView) {
            @Override
            public void success(Object obj) {
                addressAddView.addAddressFinish((AddressDO) (obj));
            }
        });
    }

    /**
     * 更新地址
     * @param addressDO 地址
     */
    @Override
    public void updateAddress(final AddressDO addressDO) {
        /// 检验地址是否合法
        if (isDataOk(addressDO) == false){
            return;
        }
        /// 调用model更新数据
        addressAddModel.updateAddress(addressDO, new AbstractOnModelFinishedListener(addressAddView) {
            @Override
            public void success(Object obj) {
                addressAddView.updateAddressFinish((AddressDO)(obj));
            }
        });
    }

    /**
     * 检验address的数据是否合法
     * 检验的项目包括收货人姓名，手机号码，收货地址，门牌号
     * 如果不合法，将直接打印提示信息
     * @param addressDO 地址
     * @return 返回一个布尔值代表地址是否合法
     */
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
