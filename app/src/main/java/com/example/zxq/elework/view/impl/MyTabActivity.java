package com.example.zxq.elework.view.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.view.MyTabView;
import com.example.zxq.elework.view.base.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyTabActivity extends BaseActivity implements MyTabView {

    private ImageView logoutImg;
    private CircleImageView avatarImg;
    private TextView nameText;
    private TextView phoneText;
    private ImageView userInfoImg;

    static public void actionStart(Context context){
        Intent intent = new Intent(context, MyTabActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab);
        //初始化组件
        initWidget();
        //展示用户信息
        showUserInfo(MyApplication.getUser());
    }

    private void initWidget() {
        //初始化组件，注册事件
        logoutImg = (ImageView)findViewById(R.id.mine_tab_logout_img);
        avatarImg = (CircleImageView)findViewById(R.id.mine_tab_user_avatar_img);
        nameText = (TextView)findViewById(R.id.mine_tab_user_name_text);
        phoneText = (TextView)findViewById(R.id.mine_tab_user_phone_text);
        userInfoImg = (ImageView)findViewById(R.id.mine_tab_user_info_img);
        logoutImg.setOnClickListener(onClickListener);
        userInfoImg.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == logoutImg){
                getLogoutDialog().show();
                return;
            }else if (view == userInfoImg){
                jumpToUserInfo();
            }
        }
    };

    private void jumpToUserInfo() {
        UserInfoActivity.actionStart(MyTabActivity.this);
    }

    private void userLogout() {
        //application清空用户数据
        MyApplication.userLogout();
        //切换到login
        LoginActivity.ActionStart(MyTabActivity.this);
    }

    private AlertDialog.Builder dialog;

    private AlertDialog.Builder getLogoutDialog(){
        if (dialog == null){
            dialog = new AlertDialog.Builder(this);
            dialog.setTitle("退出登录");
            dialog.setMessage("是否退出登录？");
            dialog.setCancelable(true);
            dialog.setNegativeButton("取消", null);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    userLogout();
                }
            });
        }
        return dialog;
    }

    @Override
    public void showUserInfo(UserDO userDO) {
        Glide.with(this).load(UrlUtil.getImageUrl(userDO.getAvatar())).into(avatarImg);
        nameText.setText(userDO.getName());
        phoneText.setText(DataDealUtil.dealTelephone(userDO.getPhone()));
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
