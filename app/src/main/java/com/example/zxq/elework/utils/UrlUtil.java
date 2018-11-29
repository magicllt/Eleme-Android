package com.example.zxq.elework.utils;

/**
 * Created by LLT on 2018/11/29.
 */

public class UrlUtil {

//    服务器url
    private static String serverUrl = "http://" + "115.196.153.223" + ":8080";

//    用户登录
    private static String userLogin = "/user/login";

    public static String getServerUrl() {return serverUrl;}

    public static String getUserLoginUrl(){
        return serverUrl + userLogin;
    }

    public static String getImageUrl(String img){
        return serverUrl + "/" + img;
    }
}
