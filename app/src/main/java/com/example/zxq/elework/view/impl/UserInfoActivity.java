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
import com.example.zxq.elework.view.UserInfoView;
import com.example.zxq.elework.view.base.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * UserInfoView的实现类
 */
public class UserInfoActivity extends BaseActivity implements UserInfoView, View.OnClickListener{

    private CircleImageView userAvatarImageView;
    private TextView userNameTextView;
    private View addressView;
    private TextView userPhoneTextView;

    /**
     * 活动的启动接口
     * @param context 上下文
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initWidget();
        showUserInfo();
    }

    /**
     * 展示用户信息
     */
    @Override
    public void showUserInfo() {
        UserDO userDO = MyApplication.getUser();
        Glide.with(this).load(UrlUtil.getImageUrl(userDO.getAvatar())).into(userAvatarImageView);
        userNameTextView.setText(userDO.getName());
        userPhoneTextView.setText(DataDealUtil.dealTelephone(userDO.getPhone()));
    }

    /**
     * 初始化界面并设置监听器
     */
    private void initWidget() {
        userAvatarImageView = (CircleImageView)findViewById(R.id.mine_data_user_avatar);
        userNameTextView = (TextView)findViewById(R.id.mine_data_user_name);
        addressView = (View)findViewById(R.id.mine_data_address_view);
        userPhoneTextView = (TextView)findViewById(R.id.mine_data_user_phone);

        addressView.setOnClickListener(this);
    }

    /**
     * 监听器diamante
     * @param view
     * 1. addressView
     *      切换到地址管理活动
     */
    @Override
    public void onClick(View view) {
        if (view == addressView){
            AddressManagerActivity.actionStart(this);
        }
    }

    /**
     * 展示消息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
