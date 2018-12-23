package com.example.zxq.elework.view.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.adapter.AddressListAdapter;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.presenter.AddressManagerPresenter;
import com.example.zxq.elework.presenter.impl.AddressManagerPresenterImpl;
import com.example.zxq.elework.view.AddressManagerView;
import com.example.zxq.elework.view.base.AbstractStateActivity;
import com.example.zxq.elework.widget.BounceLoadingView;

import java.util.ArrayList;
import java.util.List;

/**
 * AddressManagerView的实现类
 */
public class AddressManagerActivity extends AbstractStateActivity implements AddressManagerView{

    private BounceLoadingView loadingView;
    private Button reloadBtn;
    private RecyclerView recyclerView;
    private View normalView;
    private View errorView;
    private View loadView;
    private AddressManagerPresenter addressManagerPresenter;
    private ImageView backImage;
    private TextView addAddressText;
    private AddressListAdapter adapter;
    private AddressListAdapter.OnViewHolderClickListener onViewHolderClickListener;

    List<AddressDO>list;

    /// 地址的tag
    static public String ADDRESS_TAG = "address";

    /// 类型的tag
    static public String TYPE = "type";

    /// 正常类型
    static public int TYPE_NORMAL = 0;

    /// 订单类型
    static public int TYPE_ORDER = 1;


    /**
     * 正常启动
     * @param context 上下文
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context, AddressManagerActivity.class);
        context.startActivity(intent);
    }

    /**
     * 订单启动模式
     * @param context 上下文
     * @param requestCode 请求代码
     */
    public static void orderActionStart(Activity context, int requestCode){
        Intent intent = new Intent(context, AddressManagerActivity.class);
        /// 设置type为订单模式
        intent.putExtra(TYPE, TYPE_ORDER);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        /// 根据当前类型选择对象的适配器监听器
        setOnViewHolderClickListener();
        /// 初始化组件
        initWidget();
        addressManagerPresenter = new AddressManagerPresenterImpl(this);
        /// 获取地址列表
        listAddress();
    }

    /**
     * 根据当前类型选择对象的适配器监听器
     */
    private void setOnViewHolderClickListener() {
        Intent intent = getIntent();
        if (intent.getIntExtra(TYPE, TYPE_NORMAL) == TYPE_NORMAL){
            onViewHolderClickListener = normalListener;
        }else{
            onViewHolderClickListener = orderListener;
        }
    }

    /**
     * 调用presenter获取地址列表
     */
    private void listAddress() {
        addressManagerPresenter.listAddress();
    }


    /**
     * 初始化组件，添加响应事件
     */
    private void initWidget() {
        normalView = (View)findViewById(R.id.normal);
        errorView = (View)findViewById(R.id.error);
        loadView = (View)findViewById(R.id.loading);

        loadingView = (BounceLoadingView) findViewById(R.id.loadingView);
        setLoadingViewParam();

        reloadBtn = (Button)findViewById(R.id.error_no_net_reload);
        reloadBtn.setOnClickListener(onClickListener);

        recyclerView = (RecyclerView)findViewById(R.id.address_manager_recycler_view);
        adapter = new AddressListAdapter(new ArrayList<AddressDO>(), this, onViewHolderClickListener);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        backImage = (ImageView)findViewById(R.id.address_manager_back_img);
        addAddressText = (TextView)findViewById(R.id.address_manager_add_address_text);
        addAddressText.setOnClickListener(onClickListener);
        backImage.setOnClickListener(onClickListener);
    }

    /**
     * 当前操作的列表项的下标
     */
    int operatePos;

    /**
     * 订单模式的监听器
     */
    private AddressListAdapter.OnViewHolderClickListener orderListener = new AddressListAdapter.OnViewHolderClickListener() {

        /**
         * 长按事件
         * 获取操作的列表项下标
         * 显示对话框
         * @param pos 响应的下标
         */
        @Override
        public void onLongClickView(int pos) {
            operatePos = pos;
            getDialog().show();
        }

        /**
         * 点击返回数据给支付界面
         * @param pos 响应的下标
         */
        @Override
        public void onClickView(int pos) {
            /// 获取地址，设置地址到intent，返回结果
            AddressDO addressDO = list.get(pos);
            Intent intent = new Intent();
            intent.putExtra(ADDRESS_TAG, addressDO);
            setResult(RESULT_OK, intent);
            finish();
        }

        /**
         * 点击编辑，跳转到添加活动界面
         * @param pos 响应的下标
         */
        @Override
        public void onClickEditImg(int pos) {
            AddressAddActivity.updateActionStart(AddressManagerActivity.this, list.get(pos), pos);
        }
    };

    private AddressListAdapter.OnViewHolderClickListener normalListener = new AddressListAdapter.OnViewHolderClickListener() {

        /**
         * 长按事件
         * 获取操作的列表项下标
         * 显示对话框
         * @param pos 响应的下标
         */
        @Override
        public void onLongClickView(int pos) {
            operatePos = pos;
            getDialog().show();
        }

        /**
         * 点击编辑，跳转到添加活动界面
         * @param pos 响应的下标
         */
        @Override
        public void onClickView(int pos) {
            AddressAddActivity.updateActionStart(AddressManagerActivity.this, list.get(pos), pos);
        }

        /**
         * 点击编辑，跳转到添加活动界面
         * @param pos 响应的下标
         */
        @Override
        public void onClickEditImg(int pos) {
            AddressAddActivity.updateActionStart(AddressManagerActivity.this, list.get(pos), pos);
        }
    };

    /**
     * 确认删除对话框
     */
    private AlertDialog.Builder dialog;

    /**
     * 获取删除对话框
     * @return 对话框
     */
    private AlertDialog.Builder getDialog(){
        if (dialog == null){
            dialog = new android.app.AlertDialog.Builder(this);
            dialog.setTitle("删除地址");
            dialog.setMessage("确定删除该收货地址?");
            dialog.setCancelable(true);
            dialog.setNegativeButton("取消", null);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                     /// 调用presenter.removeAddress()删除地址
                    addressManagerPresenter.removeAddress(operatePos, list.get(operatePos).getId());
                }
            });
        }
        return dialog;
    }


    /**
     * 设置加载动画组件的参数
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
     * 点击事件的OnClickListener
     * reloadBtn: 重新发起网络请求
     * backImg: 结束当前活动
     * addAddressText: 跳转到添加地址活动
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view ==reloadBtn){
                listAddress();
                return;
            }else if (view == backImage){
                finish();
                return;
            }else if (view == addAddressText){
                AddressAddActivity.addActionStart(AddressManagerActivity.this);
            }
        }
    };

    /**
     * 活动返回数据调用
     * @param requestCode 请求的拜纳姆
     * @param resultCode 结果代码
     * @param intent intent分装数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode){
            /// 添加类型 获取地址，调用notifyAddData()通知添加了活动
            case AddressAddActivity.TYPE_ADD:{
                if (resultCode == RESULT_OK){
                    AddressDO addressDO = (AddressDO)intent.getSerializableExtra(AddressAddActivity.ADDRESS_TAG);
                    notifyAddData(addressDO);
                }
                break;
            }
            /// 修改类型 获取地址，调用notifyUpdateData()通知修改了活动
            case AddressAddActivity.TYPE_UPDATE:{
                if (resultCode == RESULT_OK){
                    AddressDO addressDO = (AddressDO)intent.getSerializableExtra(AddressAddActivity.ADDRESS_TAG);
                    int pos = intent.getIntExtra(AddressAddActivity.POS_TAG, 0);
                    notifyUpdateData(addressDO, pos);
                }
                break;
            }
        }
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
    public void notifyAddData(AddressDO addressDO) {
        adapter.addListData(addressDO);
    }

    @Override
    public void notifyRemoveData(int pos) {
        adapter.removeListData(pos);
    }

    @Override
    public void notifyUpdateData(AddressDO addressDO, int pos) {
        list.set(pos, addressDO);
        adapter.updateListData(addressDO, pos);
    }

    @Override
    public void notifyListData(List<AddressDO> list) {
        this.list = list;
        adapter.addAll(list);
    }
}
