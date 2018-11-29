package com.example.zxq.elework.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.google.gson.Gson;

/**
 * Created by LLT on 2018/11/29.
 */

public class MyApplication extends Application{

    private static Context context;

    private static UserDO user;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
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

    //将对象保存到pref
    private static void saveUserToPref(UserDO user){
        SharedPreferences.Editor editor = getContext().getSharedPreferences("userData", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        editor.putString("user", userString);
        editor.apply();
    }

    //从pref里面导出对象
    private static UserDO loadUserFromPref(){
        SharedPreferences pref = getContext().getSharedPreferences("userData", MODE_PRIVATE);
        String userString = pref.getString("user", "");
        if (ValidatorUtil.isEmpty(userString) == false){
            Gson gson = new Gson();
            UserDO user = gson.fromJson(userString, UserDO.class);
            return user;
        }
        return null;
    }
}


