package com.example.zxq.elework.utils;

import android.widget.Toast;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.result.Result;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by LLT on 2018/11/3.
 * 基于OkHttp的Http工具类
 * 功能：发送get请求，post请求，发送带json数据的post请求
 */
public class HttpUtil {

    /**
     * 发送get请求
     * @param address 请求的url
     * @param callback 回调接口
     */
    public static void sendOkHttpGetRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 发送post请求
     * @param address 请求的url
     * @param body 请求的参数体
     * @param callback 回调接口
     */
    public static void sendOkHttpPostRequest(String address, RequestBody body, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 将请求参数格式化为json(gson实现)，发送带json数据的post请求
     * @param address 请求url
     * @param obj 需要格式化的对象
     * @param callback 回调接口
     */
    public static void sendJsonRequest(String address, Object obj, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        /// json化对象
        String json = BeanUtil.object2Json(obj);
        /// 设置请求体的头信息
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        /// 创建一个请求对象
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        /// 发送请求获取响应
        client.newCall(request).enqueue(callback);
    }

}
