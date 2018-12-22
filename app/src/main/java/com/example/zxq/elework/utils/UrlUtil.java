package com.example.zxq.elework.utils;

import com.example.zxq.elework.application.MyApplication;

/**
 * Created by LLT on 2018/11/29.
 */

public class UrlUtil {

//    服务器url
    private static String serverUrl = "http://" + "60.186.66.175" + ":8080";

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

//    保存订单
    private static String orderSave = "order/save";

//    获取订单列表信息
    private static String orderList = "order/list";

//    获取订单详情
    private static  String orderDetail = "order/detail/%d";

//    获取店铺信息和商品
    private static String shopAndGoods = "shop/shopAndGoods/%d";

//    获取商家列表信息
    private static String shopList = "shop/page?pageNum=%d&pageSize=%d";

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

    public static String getShopList(int pageNum, int pageSize) {return String.format(shopList, pageNum, pageSize);}

    public static String getOrderSave() {
        return serverUrl + "/" + orderSave;
    }

    public static String getOrderList() {
        return orderList;
    }

    public static String getOrderDetail(int id) {
        return String.format(orderDetail, id);
    }

    public static String getShopAndGoods(int id) {
        return String.format(shopAndGoods, id);
    }
}
