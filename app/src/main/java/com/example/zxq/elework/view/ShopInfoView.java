package com.example.zxq.elework.view;

import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.view.base.BaseView;

/**
 * Created by LLT on 2018/12/20.
 * 商家信息对应的view
 */

public interface ShopInfoView extends BaseView{

    /**
     * 将商家信息渲染到对应的组件上
     * @param data 商家信息
     */
    void showShopInfo(ShopAndGoodsDO data);
}
