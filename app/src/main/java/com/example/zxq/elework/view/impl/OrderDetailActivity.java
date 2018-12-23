package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.OrderItemAdapter;
import com.example.zxq.elework.domain.OrderDetailDO;
import com.example.zxq.elework.domain.OrderItemAndGoodsDO;
import com.example.zxq.elework.domain.OrderPayParam;
import com.example.zxq.elework.presenter.OrderDetailPresenter;
import com.example.zxq.elework.presenter.impl.OrderDetailPresenterImpl;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.view.OrderDetailView;
import com.example.zxq.elework.view.base.AbstractStateActivity;
import com.example.zxq.elework.widget.BounceLoadingView;

import java.util.ArrayList;

/**
 * OrderDetailView的实现类
 */
public class OrderDetailActivity extends AbstractStateActivity implements OrderDetailView{

    /// id的tag
    static String ID_TAG = "id";

    /// 订单的id
    int id;

    /// 订单详情
    OrderDetailDO orderDetailDO;

    private View normalView;
    private View errorView;
    private View loadView;
    private BounceLoadingView loadingView;
    private Button reloadBtn;
    private TextView shopNameText;
    private Button orderAgainBtn;
    private RecyclerView goodsList;
    private TextView moneyText;
    private TextView addressHouseText;
    private TextView recepientText;
    private TextView orderNumText;
    private TextView orderTimeText;
    private OrderItemAdapter adapter;
    private OrderDetailPresenter orderDetailPresenter;

    /**
     * 活动的启动接口
     * @param context 上下文
     * @param id 订单的id
     */
    static void actionStart(Context context, int id){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(ID_TAG, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra(ID_TAG, 0);
        initWidget();
        orderDetailPresenter = new OrderDetailPresenterImpl(this);
        getOrderDetail();
    }

    /**
     * 初始化界面，设置监听器
     */
    private void initWidget() {
        normalView = (View)findViewById(R.id.activity_order_detail_normal);
        errorView = (View)findViewById(R.id.activity_order_detail_error);
        loadView = (View)findViewById(R.id.activity_order_detail_load);

        loadingView = (BounceLoadingView)findViewById(R.id.loadingView);
        setLoadingViewParam();
        reloadBtn = (Button)findViewById(R.id.error_no_net_reload);
        reloadBtn.setOnClickListener(onClickListener);

        shopNameText = (TextView)findViewById(R.id.order_detail_shop_name_text);
        orderAgainBtn = (Button)findViewById(R.id.order_detail_order_again);
        orderAgainBtn.setOnClickListener(onClickListener);
        goodsList = (RecyclerView)findViewById(R.id.order_detail_goods_list);
        moneyText = (TextView)findViewById(R.id.order_detail_money_text);
        addressHouseText = (TextView)findViewById(R.id.order_detail_address_house_text);
        recepientText = (TextView)findViewById(R.id.order_detail_recepient_info_text);
        orderNumText = (TextView)findViewById(R.id.order_detail_order_num_text);
        orderTimeText = (TextView)findViewById(R.id.order_detail_order_time_text);

        adapter = new OrderItemAdapter(new ArrayList<OrderItemAndGoodsDO>(), this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        goodsList.setLayoutManager(manager);
        goodsList.setAdapter(adapter);
    }

    /**
     * 调用presenter获取订单详情
     */
    void getOrderDetail(){
        orderDetailPresenter.getOrderDetail(id);
    }

    /**
     * 点击事件的监听器
     * 1. reloadBtn: 重新获取数据
     * 2. orderAgin： 再来一单
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == reloadBtn){
                getOrderDetail();
                return;
            }else if (view == orderAgainBtn){
                jumpToOrderAgin();
            }
        }
    };

    /**
     * 再来一单
     * 设置OrderPayParam，调用OrderPayActivity的启动接口
     */
    private void jumpToOrderAgin() {
        OrderPayParam param = new OrderPayParam();
        param.setShopDO(orderDetailDO.getShop());
        param.setTotalAmount(orderDetailDO.getTotalAmount());
        param.setGoods(orderDetailDO.getOrderItems());
        OrderPayActivity.actionStart(this, param);
    }

    /**
     * 设置加载组件的动画参数
     */
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

    /**
     * 显示消息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回正常的界面
     * @return
     */
    @Override
    public View getNormalView() {
        return normalView;
    }

    /**
     * 返回异常的界面
     * @return
     */
    @Override
    public View getErrorView() {
        return errorView;
    }

    /***
     * 返回加载的界面
     * @return
     */
    @Override
    public View getLoadingView() {
        return loadView;
    }

    /**
     * 展示订单详情的数据
     * @param orderDetail 订单详情
     */
    @Override
    public void showOrderDetailInfo(OrderDetailDO orderDetail) {

        this.orderDetailDO = orderDetail;
        shopNameText.setText(orderDetail.getShop().getName());
        adapter.addData(orderDetail.getOrderItems());
        moneyText.setText(DataDealUtil.convertToMoney(orderDetail.getTotalAmount()));
        addressHouseText.setText(orderDetail.getAddress().getAddress() + " " + orderDetail.getAddress().getHouseNum());
        recepientText.setText(String.format("%s(%s) %s", orderDetail.getAddress().getName(),
                        orderDetail.getAddress().getGender(), orderDetail.getAddress().getPhone()));
        orderNumText.setText("" + orderDetail.getId());
        orderTimeText.setText(DataDealUtil.formatDate(orderDetail.getOrderTime()));
    }
}
