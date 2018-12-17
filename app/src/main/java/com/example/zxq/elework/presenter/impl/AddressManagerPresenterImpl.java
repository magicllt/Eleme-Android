package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.model.listener.OnModelFinishedListener;
import com.example.zxq.elework.model.AddressManagerModel;
import com.example.zxq.elework.model.impl.AddressManagerModelImpl;
import com.example.zxq.elework.presenter.AddressManagerPresenter;
import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.AddressManagerView;

import java.util.List;

/**
 * Created by LLT on 2018/12/13.
 */

public class AddressManagerPresenterImpl implements AddressManagerPresenter {

    AddressManagerView addressManagerView;
    private final AddressManagerModel addressManagerModel;

    public AddressManagerPresenterImpl(AddressManagerView addressManagerView){

        this.addressManagerView = addressManagerView;
        addressManagerModel = new AddressManagerModelImpl();
    }

    @Override
    public void listAddress() {
        addressManagerView.showLoadingView();
        addressManagerModel.listAddress(new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                addressManagerView.notifyListData((List<AddressDO>)(obj));
                addressManagerView.showNromalView();
            }

            @Override
            public void error(String msg) {
                addressManagerView.showMsg(msg);
                addressManagerView.showErrorView();
            }

            @Override
            public void noNet() {
                addressManagerView.showMsg(StringUtil.NO_NETWORK);
                addressManagerView.showErrorView();
            }
        });
    }

    @Override
    public void saveAddress() {

    }

    @Override
    public void removeAddress(final int pos, int id) {
        addressManagerModel.removeAddress(id, new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                addressManagerView.notifyRemoveData(pos);
            }

            @Override
            public void error(String msg) {
                addressManagerView.showMsg("删除失败");
            }

            @Override
            public void noNet() {
                addressManagerView.showMsg(StringUtil.NO_NETWORK);
            }
        });
    }

    @Override
    public void updateAddress() {

    }
}
