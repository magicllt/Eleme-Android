package com.example.zxq.elework.view.impl;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.ShopGoodsListAdapter;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.GoodsDO;
import com.example.zxq.elework.domain.OrderItemAndGoodsDO;
import com.example.zxq.elework.domain.OrderItemVO;
import com.example.zxq.elework.domain.OrderPayParam;
import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.domain.ShopAndGoodsDO;
import com.example.zxq.elework.domain.ShopDO;
import com.example.zxq.elework.presenter.ShopMainPresenter;
import com.example.zxq.elework.presenter.impl.ShopMainPresenterImpl;
import com.example.zxq.elework.utils.BeanUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.view.OrderPayView;
import com.example.zxq.elework.view.ShopMainView;
import com.example.zxq.elework.view.base.AbstractStateActivity;
import com.example.zxq.elework.widget.BounceLoadingView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShopMainActivity extends AbstractStateActivity implements ShopMainView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private String[] titles = new String[]{"点餐", "商家"};
    Fragment[] fragments = new Fragment[2];

    static String ID_TAG = "id";

    int id;

    private View normalView;
    private View errorView;
    private View loadView;

    private BounceLoadingView loadingView;
    private Button reloadBtn;

    private CollapsingToolbarLayout toobarTitleText;
    private ImageView shopImg;

    private ImageView cartImg;
    private TextView cartInfoText;
    private TextView payInvalidText;
    private TextView payValidText;

    ShopAndGoodsDO shopAndGoodsData;
    private ShopMainPresenter shopMainPresenter;
    private List<Integer> goodsNumList;
    private BigDecimal totalPrice;
    private int goodsNum;

    static public void actionStart(Context context, int id){
        Intent intent = new Intent(context, ShopMainActivity.class);
        intent.putExtra(ID_TAG, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        id = getIntent().getIntExtra(ID_TAG, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.shop_menu_toolbar);
        setSupportActionBar(toolbar);
        totalPrice = new BigDecimal(0);
        goodsNum = 0;
        initWidget();
        shopMainPresenter = new ShopMainPresenterImpl(this);
        getShopAndGoods();
    }

    private void initWidget(){
        normalView = (View)findViewById(R.id.activity_shop_main_normal_view);
        errorView = (View)findViewById(R.id.activity_shop_main_error_view);
        loadView = (View)findViewById(R.id.activity_shop_main_load_view);

        loadingView = (BounceLoadingView) findViewById(R.id.loadingView);
        setLoadingViewParam();
        reloadBtn = (Button)findViewById(R.id.error_no_net_reload);
        reloadBtn.setOnClickListener(onClickListener);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        setTabViewParam();
        toobarTitleText = (CollapsingToolbarLayout)findViewById(R.id.shop_main_shop_name_text);
        shopImg = (ImageView)findViewById(R.id.shop_main_shop_img);
        cartImg = (ImageView)findViewById(R.id.shop_main_cart_img);
        cartInfoText = (TextView)findViewById(R.id.shop_main_cart_info_text);
        payInvalidText = (TextView)findViewById(R.id.shop_main_pay_invalid_text);
        payValidText = (TextView)findViewById(R.id.shop_main_pay_valid_text);
        payValidText.setOnClickListener(onClickListener);
        cartEmptyState();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == reloadBtn) {
                getShopAndGoods();
            }
            else if (view == payValidText){
                jumpToNewOrder();
            }
        }
    };

    private void jumpToNewOrder() {
        OrderPayParam orderPayParam = getOrderPayParam();
        OrderPayActivity.actionStart(this, orderPayParam);
    }

    private OrderPayParam getOrderPayParam(){
        OrderPayParam param = new OrderPayParam();
        ShopDO shopDO = new ShopDO();
        shopDO = BeanUtil.<ShopAndGoodsDO,ShopDO>modelAconvertoB(shopAndGoodsData, ShopDO.class);
        param.setShopDO(shopDO);
        List<OrderItemAndGoodsDO>list = new ArrayList<OrderItemAndGoodsDO>();
        for (int i = 0; i < shopAndGoodsData.getGoods().size(); ++i){
            if (goodsNumList.get(i) != 0){
                OrderItemAndGoodsDO data = new OrderItemAndGoodsDO();
                data.setGoods(shopAndGoodsData.getGoods().get(i));
                data.setNum(goodsNumList.get(i));
                list.add(data);
            }
        }
        param.setGoods(list);
        param.setTotalAmount(totalPrice);
        return param;
    }


    private ShopGoodsListAdapter.onGoodsNumChangeListener onGoodsNumChangeListener = new ShopGoodsListAdapter.onGoodsNumChangeListener() {
        @Override
        public void onChange(int idx, int num) {
            if (goodsNumList.get(idx) != 0 && num == 0){
                --goodsNum;
            }
            if (goodsNumList.get(idx) == 0 && num != 0){
                ++goodsNum;
            }
            totalPrice = totalPrice.subtract(shopAndGoodsData.getGoods().get(idx).getPrice().multiply(new BigDecimal(goodsNumList.get(idx))));
            goodsNumList.set(idx, num);
            totalPrice = totalPrice.add(shopAndGoodsData.getGoods().get(idx).getPrice().multiply(new BigDecimal(goodsNumList.get(idx))));
            if (goodsNum == 0){
                cartEmptyState();
            }else{
                cartNotEmptyState();
            }
            Log.d(TAG, "onChange: " + String.format("cnt: %d    totalPrice: %s", goodsNum, totalPrice.toString()));
        }
    };

    public ShopGoodsListAdapter.onGoodsNumChangeListener getOnGoodsNumChangeListener() {
        return onGoodsNumChangeListener;
    }

    public List<GoodsDO> getGoodsList(){
        return shopAndGoodsData.getGoods();
    }

    public ShopAndGoodsDO getShopAndGoodsData(){
        return shopAndGoodsData;
    }

    public List<Integer> getGoodsNumList() {
        return goodsNumList;
    }

    private void setTabViewParam(){
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab());
        }

        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                replaceFragment(fragments[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setLoadingViewParam() {
        loadingView.addBitmap(R.mipmap.loading4);
        loadingView.addBitmap(R.mipmap.loading5);
        loadingView.addBitmap(R.mipmap.loading6);
        loadingView.addBitmap(R.mipmap.loading7);
        loadingView.addBitmap(R.mipmap.loading8);
        loadingView.addBitmap(R.mipmap.loading9);
        loadingView.setShadowColor(Color.LTGRAY);
        loadingView.setDuration(700);
        loadingView.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }

    private void replaceFragment(android.support.v4.app.Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.shop_main_framelayout, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getNormalView() {
        return normalView;
    }

    @Override
    public View getErrorView() {
        return errorView;
    }

    @Override
    public View getLoadingView() {
        return loadView;
    }

    @Override
    public void changeCartState(boolean isEmpty) {
        if (isEmpty){
            cartEmptyState();
        }else{
            cartNotEmptyState();
        }
    }

    @Override
    public void onGetShopAndGoodsSuccess(ShopAndGoodsDO data) {
        //数据的设置
        shopAndGoodsData = data;
        Log.d(TAG, data.toString());

        //商品数量的设置
        goodsNumList = new ArrayList<>();
        for (int i = 0; i < data.getGoods().size(); ++i){
            goodsNumList.add(0);
        }
        //设置碎片
        fragments[0] = new ShopGoodsListFragment();
        fragments[1] = new ShopInfoFragment();
        tabLayout.getTabAt(0).select();
        replaceFragment(fragments[0]);

        //布局的设置
        toobarTitleText.setTitle(shopAndGoodsData.getName());
        Glide.with(this).load(UrlUtil.getImageUrl(shopAndGoodsData.getImg())).into(shopImg);
    }

    private void getShopAndGoods(){
        shopMainPresenter.getShopAndGoods(id);
    }

    private void cartEmptyState(){
        cartImg.setBackground(getDrawable(R.drawable.cart_icon_empty_e));
        cartInfoText.setText("购物车为空");
        payInvalidText.setVisibility(View.VISIBLE);
        payValidText.setVisibility(View.GONE);
    }

    private void cartNotEmptyState(){
        cartImg.setBackground(getDrawable(R.drawable.cart_icon_not_empty_normal));
        cartInfoText.setText("￥" + totalPrice.toString());
        payInvalidText.setVisibility(View.GONE);
        payValidText.setVisibility(View.VISIBLE);
    }
}

