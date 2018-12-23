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
 * AddressManagerPresenter的实现类
 */
public class AddressManagerPresenterImpl implements AddressManagerPresenter {

    AddressManagerView addressManagerView;
    private final AddressManagerModel addressManagerModel;

    public AddressManagerPresenterImpl(AddressManagerView addressManagerView){

        this.addressManagerView = addressManagerView;
        addressManagerModel = new AddressManagerModelImpl();
    }

    /**
     * 获取地址列表
     */
    @Override
    public void listAddress() {
        /// 加载数据，先开启加载动画
        addressManagerView.showLoadingView();
        /// 调用model获取地址列表
        addressManagerModel.listAddress(new OnModelFinishedListener() {
            @Override
            public void success(Object obj) {
                /// 将获取的数据交给view，同时显示正常的页面
                addressManagerView.notifyListData((List<AddressDO>)(obj));
                addressManagerView.showNromalView();
            }

            @Override
            public void error(String msg) {
                /// 打印错误信息，同时显示加载失败的界面
                addressManagerView.showMsg(msg);
                addressManagerView.showErrorView();
            }

            @Override
            public void noNet() {
                /// 打印网络异常的信息，同时显示加载失败的界面
                addressManagerView.showMsg(StringUtil.NO_NETWORK);
                addressManagerView.showErrorView();
            }
        });
    }


    /**
     * 移除地址
     * @param pos 适配器的下标
     * @param id 要移除的地址的id
     */
    @Override
    public void removeAddress(final int pos, int id) {
        /// 调用model
        addressManagerModel.removeAddress(id, new OnModelFinishedListener() {

            @Override
            public void success(Object obj) {
                /// 通知view移除成功，返回移除的下标
                addressManagerView.notifyRemoveData(pos);
            }

            @Override
            public void error(String msg) {
                /// 调用view打印错误的信息
                addressManagerView.showMsg("删除失败");
            }

            @Override
            public void noNet() {
                /// 调用view显示网络异常的信息
                addressManagerView.showMsg(StringUtil.NO_NETWORK);
            }
        });
    }

}
