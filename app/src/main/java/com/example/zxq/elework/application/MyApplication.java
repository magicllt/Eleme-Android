package com.example.zxq.elework.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.utils.UrlUtil;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.google.gson.Gson;
import com.itheima.retrofitutils.ItheimaHttp;

/**
 * Created by LLT on 2018/11/29.
 * 本项目的Application
 */

public class MyApplication extends Application{

    private static Context context;

    private static UserDO user;

    private static final String PREF_LOC = "userData";

    private static final String USER_TAG = "user";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ItheimaHttp.init(this, UrlUtil.getServerUrl());
    }

    /**
     * 返回应用的上下文
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 返回用户的信息
     * @return
     */
    public static UserDO getUser() {
        /// 如果user为null，试着从preference中获取用户的信息
        if (MyApplication.user == null){
            setUser(loadUserFromPref());
        }
        return MyApplication.user;
    }

    /**
     * 保存用户的信息
     * @param user
     */
    public static void setUser(UserDO user) {
        MyApplication.user = user;
        /// 将用户信息备份到preferences中
        saveUserToPref(user);
    }

    /**
     * 用户登出的操作
     */
    public static void userLogout(){
        /// 清空保存在preference中的用户数据
        SharedPreferences.Editor editor = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE).edit();
        editor.clear().commit();
        MyApplication.user = null;
    }


    /**
     * 将用户信息保存到preference,用于完成用户直接登录功能
     * @param user 用户的信息
     */
    private static void saveUserToPref(UserDO user){
        SharedPreferences.Editor editor = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE).edit();
        /// 用户数据转化成json,再进行保存
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        editor.putString(USER_TAG, userString);
        editor.apply();
    }

    /**
     * 从preference中导出用户的信息
     * @return 从preference中导出用户数据
     */
    private static UserDO loadUserFromPref(){
        SharedPreferences pref = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE);
        String userString = pref.getString(USER_TAG, "");
        /// 如果有用户数据就解析数据，将json解析成User类
        if (ValidatorUtil.isEmpty(userString) == false){
            Gson gson = new Gson();
            UserDO user = gson.fromJson(userString, UserDO.class);
            return user;
        }
        //返回null
        return null;
    }
}


