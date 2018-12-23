package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.view.ShopInfoView;

/**
 * ShopInfoView的实现类
 */
public class ShopInfoFragment extends Fragment implements ShopInfoView{

    private ShopAndGoodsDO data;
    private View fragmentView;
    private Context context;
    private ImageView shopImg;
    private TextView nameText;
    private TextView categoryText;
    private TextView addressText;
    private TextView phoneText;
    private TextView runTimeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_shop_info, container, false);
        context = getActivity();
        initWidget();
        ShopMainActivity activity = (ShopMainActivity)(context);
        showShopInfo(activity.getShopAndGoodsData());
        return fragmentView;
    }

    /**
     * 初始化组件，完成事件绑定
     */
    private void initWidget() {
        shopImg = (ImageView)fragmentView.findViewById(R.id.shop_data_tab_shop_img);
        nameText = (TextView)fragmentView.findViewById(R.id.shop_data_tab_shop_name_text);
        categoryText = (TextView)fragmentView.findViewById(R.id.shop_data_tab_category_text);
        addressText = (TextView)fragmentView.findViewById(R.id.shop_data_tab_address_text);
        phoneText = (TextView)fragmentView.findViewById(R.id.shop_data_tab_shop_phone_text);
        runTimeText = (TextView)fragmentView.findViewById(R.id.shop_data_tab_run_time_text);
    }

    /**
     * 显示消息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示店铺信息
     * @param data 商家信息
     */
    @Override
    public void showShopInfo(ShopAndGoodsDO data) {
        this.data = data;
        Glide.with(context).load(UrlUtil.getImageUrl(data.getImg())).into(shopImg);
        nameText.setText(data.getName());
        categoryText.setText(data.getCategory());
        addressText.setText(data.getAddress());
        phoneText.setText(data.getPhone());
        runTimeText.setText(data.getStartTime() + "-" + data.getCloseTime());
    }
}
