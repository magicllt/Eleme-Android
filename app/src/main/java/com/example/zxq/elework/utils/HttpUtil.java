package com.example.zxq.elework.utils;

import android.widget.Toast;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.result.Result;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by LLT on 2018/11/3.
 */

//发送http请求的工具类
public class HttpUtil {

    public static void sendOkHttpGetRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpPostRequest(String address, RequestBody body, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpDeleteRequest(String address, RequestBody body, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .delete(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpPutRequest(String address, RequestBody body, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .put(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void accessServerFail(){
        Toast.makeText(MyApplication.getContext(), "访问服务器失败", Toast.LENGTH_SHORT).show();
    }

    public static void ResultFail(Result result){
        String str = String.format("错误代码: %d    消息: %s", result.getCode(), result.getMsg());
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
