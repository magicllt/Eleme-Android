package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.OrderItemAdapter;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.domain.OrderItemAndGoodsDO;
import com.example.zxq.elework.domain.OrderItemVO;
import com.example.zxq.elework.domain.OrderPayParam;
import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.presenter.OrderPayPresenter;
import com.example.zxq.elework.presenter.impl.OrderPayPresenterImpl;
import com.example.zxq.elework.view.OrderPayView;
import com.example.zxq.elework.view.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OrderPayActivity extends BaseActivity implements OrderPayView {

    static public String PARAM_TAG = "param";

    OrderPayParam orderInfo;
    
    AddressDO addressDO;
    
    final int ADDRESS_REQUEST_CODE = 0;

    private Button backBtn;
    private TextView addressText;
    private TextView recepientText;
    private TextView shopNameText;
    private RecyclerView recyclerView;
    private TextView priceText;
    private TextView cartPriceText;
    private TextView assurePayText;
    private OrderItemAdapter adapter;

    OrderPayPresenter orderPayPresenter;


    static public void actionStart(Context context, OrderPayParam param){
        Intent intent = new Intent(context, OrderPayActivity.class);
        Gson gson = new Gson();
        String jsonString = gson.toJson(param);
        intent.putExtra(PARAM_TAG, jsonString);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        parseIntent();
        initWidget();
        showOrderInfo(orderInfo);
        orderPayPresenter = new OrderPayPresenterImpl(this);
    }

    private void initWidget() {
        backBtn = (Button)findViewById(R.id.order_pay_back_btn);
        addressText = (TextView)findViewById(R.id.order_pay_address_text);
        recepientText = (TextView)findViewById(R.id.order_pay_recepient_name_text);
        shopNameText = (TextView)findViewById(R.id.order_pay_shop_name_text);
        recyclerView = (RecyclerView)findViewById(R.id.order_pay_recycler_view);
        priceText = (TextView)findViewById(R.id.order_pay_price_text);
        cartPriceText = (TextView)findViewById(R.id.order_pay_cart_price_text);
        assurePayText = (TextView)findViewById(R.id.order_pay_assure_pat_text);

        backBtn.setOnClickListener(onClickListener);
        addressText.setOnClickListener(onClickListener);
        assurePayText.setOnClickListener(onClickListener);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new OrderItemAdapter(new ArrayList<OrderItemAndGoodsDO>(), this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == backBtn){
                finish();
                return;
            }else if (view == addressText){
                AddressManagerActivity.orderActionStart(OrderPayActivity.this, ADDRESS_REQUEST_CODE);
                return;
            }else if (view == assurePayText){
                submitOrder();
                return;
            }
        }
    };
    
    private void submitOrder(){
        if (addressDO == null){
            Toast.makeText(this, "您还没选择地址", Toast.LENGTH_SHORT).show();
            return;
        }else{
            OrderVo order = newOrder();
            orderPayPresenter.submitOrder(order);
        }
    }
    
    OrderVo newOrder(){
        OrderVo order = new OrderVo();
        order.setUserId(MyApplication.getUser().getId());
        order.setAddressId(addressDO.getId());
        order.setShopId(orderInfo.getShopDO().getId());
        order.setTotalAmount(orderInfo.getTotalAmount());
        List<OrderItemVO>list = new ArrayList<OrderItemVO>();
        for (OrderItemAndGoodsDO item: orderInfo.getGoods()){
            OrderItemVO listItem = new OrderItemVO();
            listItem.setGoodsId(item.getGoods().getId());
            listItem.setNum(item.getNum());
            list.add(listItem);
        }
        order.setOrderItems(list);
        return order;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode){
            case ADDRESS_REQUEST_CODE:{
                addressDO = (AddressDO) intent.getSerializableExtra(AddressManagerActivity.ADDRESS_TAG);
                onAddressSelect(addressDO);
                break;
            }
        }
    }

    private void parseIntent() {
        Intent intent = getIntent();
        String jsonString = intent.getStringExtra(PARAM_TAG);
        this.orderInfo = new Gson().fromJson(jsonString, OrderPayParam.class);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddressSelect(AddressDO addressDO) {
        showAddressInfo(addressDO);
    }

    @Override
    public void submitSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PaySuccessActivity.actionStart(OrderPayActivity.this);
            }
        });
    }

    @Override
    public void submitFail(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OrderPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showOrderInfo(OrderPayParam param) {
        shopNameText.setText(param.getShopDO().getName());
        adapter.addData(param.getGoods());
        priceText.setText(param.getTotalAmount().toString());
        cartPriceText.setText(param.getTotalAmount().toString());
    }

    @Override
    public void showAddressInfo(AddressDO addressDO) {
        addressText.setText(addressDO.getAddress() + " " + addressDO.getHouseNum());
        recepientText.setText(String.format("%s(%s) %s", addressDO.getName(), addressDO.getGender(), addressDO.getPhone()));
    }
}
