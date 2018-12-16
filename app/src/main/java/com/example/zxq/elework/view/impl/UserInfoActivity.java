package com.example.zxq.elework.view.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.view.AddressManagerView;
import com.example.zxq.elework.view.UserInfoView;
import com.example.zxq.elework.view.base.BaseActivity;

import java.net.Inet4Address;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity implements UserInfoView, View.OnClickListener{

    private CircleImageView userAvatarImageView;
    private TextView userNameTextView;
    private View addressView;
    private TextView userPhoneTextView;

    public static void actionStart(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        // TODO: 2018/12/13 用户未登录的问题
        initWidget();
        showUserInfo();
    }

    @Override
    public void showUserInfo() {
        UserDO userDO = MyApplication.getUser();
        Glide.with(this).load(UrlUtil.getImageUrl(userDO.getAvatar())).into(userAvatarImageView);
        userNameTextView.setText(userDO.getName());
        userPhoneTextView.setText(DataDealUtil.dealTelephone(userDO.getPhone()));
    }

    private void initWidget() {
        userAvatarImageView = (CircleImageView)findViewById(R.id.mine_data_user_avatar);
        userNameTextView = (TextView)findViewById(R.id.mine_data_user_name);
        addressView = (View)findViewById(R.id.mine_data_address_view);
        userPhoneTextView = (TextView)findViewById(R.id.mine_data_user_phone);

        addressView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == addressView){
            AddressManagerActivity.actionStart(this);
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
