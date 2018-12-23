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
 * TakeAwayPresenter的实现类
 */
public class TakeAwayPresenterImpl implements TakeAwayPresenter {

    TakeAwayView takeAwayView;

    TakeAwayModel takeAwayModel;

    /// 维护分页的信息
    private final PageInfoBreif pageInfoBreif;

    public TakeAwayPresenterImpl(TakeAwayView takeAwayView){
        this.takeAwayView = takeAwayView;
        pageInfoBreif = new PageInfoBreif();
        /// 初始化分页， 页码为0， 页数为1， 页大小为5
        pageInfoBreif.setPageNum(0);
        pageInfoBreif.setPages(1);
        pageInfoBreif.setPageSize(5);
        takeAwayModel = new TakeAwayModelImpl();
    }

    /**
     * 获取更多的数据
     */
    @Override
    public void loadMoreData() {
        /// 判断是否还有数据可以获取
        if (pageInfoBreif.getPageNum() < pageInfoBreif.getPages()){
            /// 传入当前页码加1，页大小
            takeAwayModel.loadMoreData(pageInfoBreif.getPageNum()+1, pageInfoBreif.getPageSize(), new AbstractOnModelFinishedListener(takeAwayView) {
                @Override
                public void success(Object obj) {
                    /// 将获取的数据传递给view，调用finishMoreData()方法通知加载数据成功，更新pageInfoView的页码和总页数
                    PageInfoBreif<ShopListItemDO>info = (PageInfoBreif<ShopListItemDO>) (obj);
                    takeAwayView.addData(info.getList());
                    pageInfoBreif.setPageNum(info.getPageNum());
                    pageInfoBreif.setPages(info.getPages());
                    takeAwayView.finishLoadMore(true);
                }
                @Override
                public void noNet() {
                    /// 调用父类方法，调用view.finishLoadMore(false)
                    super.noNet();
                    takeAwayView.finishLoadMore(false);
                }

                @Override
                public void error(String msg) {
                    /// 调用父类方法，调用view.finishLoadMore(false)
                    super.error(msg);
                    takeAwayView.finishLoadMore(false);
                }
            });
        }else{
            /// 没有更多的数据可以获取，调用view.finishLoadMoreDataWithoutMoreData(), 提示更多数据可以获取
            takeAwayView.finsihLoadMoreWithoutMoreData();
        }
    }
}

