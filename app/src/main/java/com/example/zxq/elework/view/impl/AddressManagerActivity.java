package com.example.zxq.elework.view.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.presenter.AddressManagerPresenter;
import com.example.zxq.elework.presenter.impl.AddressManagerPresenterImpl;
import com.example.zxq.elework.view.AddressManagerView;
import com.example.zxq.elework.view.base.BaseActivity;
import com.example.zxq.elework.widget.BounceLoadingView;

import java.util.List;

public class AddressManagerActivity extends BaseActivity implements AddressManagerView, View.OnClickListener{

    private BounceLoadingView loadingView;
    private Button reloadBtn;
    private RecyclerView recyclerView;
    private View normalView;
    private View errorView;
    private View loadView;
    private AddressManagerPresenter addressManagerPresenter;
    private ImageView backImage;
    private TextView addAddressText;
    private AddressAdapter adapter;

    public static void actionStart(Context context){
        Intent intent = new Intent(context, AddressManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        initWidget();
        addressManagerPresenter = new AddressManagerPresenterImpl(this);
        addressManagerPresenter.listAddress();
    }

    private void initWidget() {
        normalView = (View)findViewById(R.id.normal);
        errorView = (View)findViewById(R.id.error);
        loadView = (View)findViewById(R.id.loading);

        loadingView = (BounceLoadingView) findViewById(R.id.loadingView);
        reloadBtn = (Button)findViewById(R.id.error_no_net_reload);
        recyclerView = (RecyclerView)findViewById(R.id.address_manager_recycler_view);
        backImage = (ImageView)findViewById(R.id.address_manager_back_img);
        addAddressText = (TextView)findViewById(R.id.address_manager_add_address_text);
        addAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressAddActivity.addActionStart(AddressManagerActivity.this);
            }
        });
        reloadBtn.setOnClickListener(this);
        backImage.setOnClickListener(this);
        setLoadingViewParam();
        loadingView.start();
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
    }


    @Override
    public void hideAll() {
        normalView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        loadView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNormal() {
        hideAll();
        normalView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        hideAll();
        loadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        hideAll();
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        adapter.updateListData(pos, addressDO);
    }

    @Override
    public void notifyListData(List<AddressDO> list) {
        adapter = new AddressAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == reloadBtn){
            addressManagerPresenter.listAddress();
            return;
        }else if (view == backImage){
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode){
            case AddressAddActivity.TYPE_ADD:{
                if (resultCode == RESULT_OK){
                    AddressDO addressDO = (AddressDO)intent.getSerializableExtra(AddressAddActivity.ADDRESS_TAG);
                    notifyAddData(addressDO);
                }
                break;
            }
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

    /**
     * 适配器代码
     */
    class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{

        private List<AddressDO> addressDOList;

        public AddressAdapter(List<AddressDO> addressDOList) {
            this.addressDOList = addressDOList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.address_unit, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.addressView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // TODO: 2018/12/16 如何将adapter和activity分离，并且viewHolde添加的监听器能够获取viewHolder的位置
                    itemPos = holder.getAdapterPosition();
                    itemId = addressDOList.get(itemPos).getId();
                    getAlertDialog().show();
                    return false;
                }
            });

            holder.addressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemPos = holder.getAdapterPosition();
                    AddressDO addressDO = addressDOList.get(itemPos);
                    AddressAddActivity.updateActionStart(AddressManagerActivity.this, addressDO, itemPos);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AddressDO addressDO = addressDOList.get(position);
            holder.setVal(addressDO);
        }

        @Override
        public int getItemCount() {
            return addressDOList.size();
        }

        private int itemPos;

        private int itemId;

        private AlertDialog.Builder dialog;

        private AlertDialog.Builder getAlertDialog(){
            if (dialog == null){
                dialog = new AlertDialog.Builder(AddressManagerActivity.this);
                dialog.setTitle("删除地址");
                dialog.setMessage("确定删除该收货地址?");
                dialog.setCancelable(true);
                dialog.setNegativeButton("取消", null);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addressManagerPresenter.removeAddress(itemPos, itemId);
                    }
                });
            }
            return dialog;
        }

        public void removeListData(int pos){
            addressDOList.remove(pos);
            notifyItemRemoved(pos);
            notifyDataSetChanged();
            notifyItemRangeChanged(pos, addressDOList.size() - pos);
        }

        public void addListData(AddressDO addressDO){
            addListData(addressDOList.size(), addressDO);
        }

        public void updateListData(int pos, AddressDO addressDO){
            removeListData(pos);
            addListData(pos, addressDO);
        }

        private void addListData(int pos, AddressDO addressDO){
            addressDOList.add(pos, addressDO);
            notifyItemInserted(pos);
            notifyDataSetChanged();
            notifyItemRangeChanged(pos, addressDOList.size() - pos);
        }


        class ViewHolder extends RecyclerView.ViewHolder{

            View addressView;

            TextView addressText;

            TextView houseNumText;

            TextView nameText;

            TextView phoneText;

            ImageView editImg;

            public ViewHolder(View itemView) {
                super(itemView);
                addressView = itemView;
                addressText = (TextView)itemView.findViewById(R.id.address_unit_address_text);
                houseNumText = (TextView)itemView.findViewById(R.id.address_unit_house_num_text);
                nameText = (TextView)itemView.findViewById(R.id.address_unit_name_text);
                phoneText = (TextView)itemView.findViewById(R.id.address_unit_phone_text);
                editImg = (ImageView)itemView.findViewById(R.id.address_unit_edit_img);
            }

            public void setVal(AddressDO addressDO){
                addressText.setText(addressDO.getAddress());
                houseNumText.setText(addressDO.getHouseNum());
                nameText.setText(String.format("%s(%s)", addressDO.getName(), addressDO.getGender()));
                phoneText.setText(addressDO.getPhone());
            }
        }
    }
}
