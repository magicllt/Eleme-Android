package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

/**
 * Created by LLT on 2018/12/20.
 * 商家对应的view
 */
public interface ShopMainView extends StateView, BaseView{

    /**
     * 购物车发生变化
     * @param isEmpty 是否为空
     */
    void changeCartState(boolean isEmpty);

    /**
     * 获取数据成功时候调用
     * @param data 获取到的数据
     */
    void onGetShopAndGoodsSuccess(ShopAndGoodsDO data);
}
