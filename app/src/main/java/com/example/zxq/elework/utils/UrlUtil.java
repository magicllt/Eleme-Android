package com.example.zxq.elework.utils;

import com.example.zxq.elework.application.MyApplication;

/**
 * Created by LLT on 2018/11/29.
 */

public class UrlUtil {

//    服务器url
    private static String serverUrl = "http://" + "115.196.146.131" + ":8080";

//    用户登录
    private static String userLogin = "/user/login";

    private static String addressList = "/address/list/%d";

    public static String getServerUrl() {return serverUrl;}

    public static String getUserLoginUrl(){
        return serverUrl + userLogin;
    }

    public static String getImageUrl(String img){
        return serverUrl + "/" + img;
    }

    public static String getAddressList(){return String.format(serverUrl + addressList, MyApplication.getUser().getId());}
}
