package com.example.zxq.elework.utils;

import com.example.zxq.elework.application.MyApplication;

/**
 * Created by LLT on 2018/11/29.
 */

public class UrlUtil {

//    服务器url
    private static String serverUrl = "http://" + "115.196.147.216" + ":8080";

//    用户登录
    private static String userLogin = "user/login";

//    用户注册
    private static String userRegister = "user/register";

//    获取用户的地址
    private static String addressList = "address/list/%d";

//    删除地址
    private static String addressDelete = "address/deleteStatus";

//    保存地址
    private static String addressSave = "address/save";

//    更新地址
    private static String addressUpdate = "address/update";

    public static String getServerUrl() {return serverUrl;}

    public static String getUserLoginUrl(){
        return userLogin;
    }

    public static String getImageUrl(String img){
        return serverUrl + "/" + img;
    }

    public static String getAddressList(){return String.format(addressList, MyApplication.getUser().getId());}

    public static String getAddressDelete() {return addressDelete;}

    public static String getAddressSave() {
        return addressSave;
    }

    public static String getAddressUpdate() {
        return addressUpdate;
    }

    public static String getUserRegister() {
        return userRegister;
    }
}
