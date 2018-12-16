//package com.example.zxq.elework.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.zxq.elework.R;
//import com.example.zxq.elework.activity.baseActivity.BaseActivity2;
//import com.example.zxq.elework.application.MyApplication;
//import com.example.zxq.elework.domain.UserDO;
//import com.example.zxq.elework.utils.UrlUtil;
//import com.example.zxq.elework.utils.ValidatorUtil;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class UserInfoActivity2 extends BaseActivity2 {
//
//    CircleImageView userAvatar;
//
//    TextView userPhone;
//
//    TextView userName;
//
//    View addressView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_info);
//        getSupportActionBar().hide();
//
//        userAvatar = (CircleImageView)findViewById(R.id.mine_data_user_avatar);
//        userPhone = (TextView)findViewById(R.id.mine_data_user_phone);
//        userName = (TextView)findViewById(R.id.mine_data_user_name);
//
//        showUserInfo(MyApplication.getUser());
//
//        addressView = (View)findViewById(R.id.mine_data_address_view);
//        //添加收货地址的响应事件
//        addressView.setOnClickListener(myOnClickListener);
//    }
//
//    private void showUserInfo(UserDO user){
//
////        设置用户头像
//        if (ValidatorUtil.isEmpty(user.getAvatar()) == false){
//            Glide.with(UserInfoActivity2.this).load(UrlUtil.getImageUrl(user.getAvatar())).into(userAvatar);
//        }
////        设置手机号码
//        userPhone.setText(user.getPhone());
////        设置用户名
//        userName.setText(user.getName());
//
//    }
//
//    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // 点击收货地址模块的响应
//            if (view == addressView){
//                //跳转到后货地址模块
//                AddressManagerActivity.actionStart(UserInfoActivity2.this);
//            }
//        }
//    };
//}
