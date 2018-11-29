package com.example.zxq.elework.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.preference.PreferenceManager;

import com.example.zxq.elework.domain.User;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by LLT on 2018/11/29.
 */

public class MyApplication extends Application{

    private static Context context;

    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static User getUser() {
        if (MyApplication.user == null){
            setUser(loadUserFromPref());
        }
        return MyApplication.user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
        saveUserToPref(user);
    }

    //将对象保存到pref
    private static void saveUserToPref(User user){
        SharedPreferences.Editor editor = getContext().getSharedPreferences("userData", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        editor.putString("user", userString);
        editor.apply();
    }

    //从pref里面导出对象
    private static User loadUserFromPref(){
        SharedPreferences pref = getContext().getSharedPreferences("userData", MODE_PRIVATE);
        String userString = pref.getString("user", "");
        if (ValidatorUtil.isEmpty(userString) == false){
            Gson gson = new Gson();
            User user = gson.fromJson(userString, User.class);
            return user;
        }
        return null;
    }
}


