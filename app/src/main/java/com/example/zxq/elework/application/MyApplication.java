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

    public static Context getContext() {
        return context;
    }

    public static UserDO getUser() {
        if (MyApplication.user == null){
            setUser(loadUserFromPref());
        }
        return MyApplication.user;
    }

    public static void setUser(UserDO user) {
        MyApplication.user = user;
        saveUserToPref(user);
    }

    public static void userLogout(){
        //清空数据
        SharedPreferences.Editor editor = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE).edit();
        editor.clear().commit();
        MyApplication.user = null;
    }

    //将对象保存到pref
    private static void saveUserToPref(UserDO user){
        SharedPreferences.Editor editor = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        editor.putString(USER_TAG, userString);
        editor.apply();
    }

    //从pref里面导出对象
    private static UserDO loadUserFromPref(){
        SharedPreferences pref = getContext().getSharedPreferences(PREF_LOC, MODE_PRIVATE);
        String userString = pref.getString(USER_TAG, "");
        //有用户数据就解析数据
        if (ValidatorUtil.isEmpty(userString) == false){
            Gson gson = new Gson();
            UserDO user = gson.fromJson(userString, UserDO.class);
            return user;
        }
        //返回null
        return null;
    }
}


