package com.example.zxq.elework.presenter.impl;

import android.widget.Toast;

import com.bumptech.glide.load.model.ModelCache;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.ShopListItemDO;
import com.example.zxq.elework.model.TakeAwayModel;
import com.example.zxq.elework.model.impl.TakeAwayModelImpl;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.presenter.TakeAwayPresenter;
import com.example.zxq.elework.result.PageInfoBreif;
import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.view.TakeAwayView;

/**
 * Created by LLT on 2018/12/17.
 */

public class TakeAwayPresenterImpl implements TakeAwayPresenter {

    TakeAwayView takeAwayView;

    TakeAwayModel takeAwayModel;

    private final PageInfoBreif pageInfoBreif;

    public TakeAwayPresenterImpl(TakeAwayView takeAwayView){
        this.takeAwayView = takeAwayView;
        pageInfoBreif = new PageInfoBreif();
        pageInfoBreif.setPageNum(0);
        pageInfoBreif.setPages(1);
        pageInfoBreif.setPageSize(5);
        takeAwayModel = new TakeAwayModelImpl();
    }

    @Override
    public void loadMoreData() {
        //还有数据可以获取
        if (pageInfoBreif.getPageNum() < pageInfoBreif.getPages()){
            takeAwayModel.loadMoreData(pageInfoBreif.getPageNum()+1, pageInfoBreif.getPageSize(), new AbstractOnModelFinishedListener(takeAwayView) {
                @Override
                public void success(Object obj) {
                    PageInfoBreif<ShopListItemDO>info = (PageInfoBreif<ShopListItemDO>) (obj);
                    takeAwayView.addData(info.getList());
                    pageInfoBreif.setPageNum(info.getPageNum());
                    pageInfoBreif.setPages(info.getPages());
                    takeAwayView.finishLoadMore(true);
                }
                @Override
                public void noNet() {
                    super.noNet();
                    takeAwayView.finishLoadMore(false);
                    Toast.makeText(MyApplication.getContext(), StringUtil.NO_NETWORK, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void error(String msg) {
                    super.error(msg);
                    takeAwayView.finishLoadMore(false);
                    Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            takeAwayView.finsihLoadMoreWithoutMoreData();
        }
    }
}

