package com.example.zxq.elework.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.User;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.utils.ValidatorUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity {

    CircleImageView userAvatar;

    TextView userPhone;

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();

        userAvatar = (CircleImageView)findViewById(R.id.mine_data_user_avatar);
        userPhone = (TextView)findViewById(R.id.mine_data_user_phone);
        userName = (TextView)findViewById(R.id.mine_data_user_name);

        showUserInfo(MyApplication.getUser());
    }

    private void showUserInfo(User user){

//        设置用户头像
        if (ValidatorUtil.isEmpty(user.getUavatar()) == false){
            Glide.with(UserInfoActivity.this).load(UrlUtil.getImageUrl(user.getUavatar())).into(userAvatar);
        }
//        设置手机号码
        userPhone.setText(user.getUphone());
//        设置用户名
        userName.setText(user.getUname());

    }
}
