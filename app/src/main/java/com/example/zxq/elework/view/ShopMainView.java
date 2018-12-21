package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.view.base.BaseView;
import com.example.zxq.elework.view.base.StateView;

/**
 * Created by LLT on 2018/12/20.
 */

public interface ShopMainView extends StateView, BaseView{

    void changeCartState(boolean isEmpty);

    void onGetShopAndGoodsSuccess(ShopAndGoodsDO data);
}
