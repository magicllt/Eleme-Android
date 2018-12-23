package com.example.zxq.elework.utils;

import com.example.zxq.elework.application.MyApplication;

/**
 * Created by LLT on 2018/11/29.
 * 管理url的工具类
 */
public class UrlUtil {

///    服务器url
    private static String serverUrl = "http:///" + "115.196.157.252" + ":8080";

///    用户登录
    private static String userLogin = "user/login";

///    用户注册
    private static String userRegister = "user/register";

///    获取用户的地址
    private static String addressList = "address/list/%d";

///    删除地址
    private static String addressDelete = "address/deleteStatus";

///    保存地址
    private static String addressSave = "address/save";

///    更新地址
    private static String addressUpdate = "address/update";

///    保存订单
    private static String orderSave = "order/save";

///    获取订单列表信息
    private static String orderList = "order/list";

///    获取订单详情
    private static  String orderDetail = "order/detail/%d";

///    获取店铺信息和商品
    private static String shopAndGoods = "shop/shopAndGoods/%d";

///    获取商家列表信息
    private static String shopList = "shop/page?pageNum=%d&pageSize=%d";

    /**
     * @return 服务器url
     */
    public static String getServerUrl() {return serverUrl;}

    /**
     * @return 用户登录url
     */
    public static String getUserLoginUrl(){
        return userLogin;
    }

    /**
     * @param img 图片的部分url
     * @return 拼接好的完整图片url
     */
    public static String getImageUrl(String img){
        return serverUrl + "/" + img;
    }

    /**
     * @return 地址列表url
     */
    public static String getAddressList(){return String.format(addressList, MyApplication.getUser().getId());}

    /**
     * @return 删除地址url
     */
    public static String getAddressDelete() {return addressDelete;}

    /**
     * @return 保存地址url
     */
    public static String getAddressSave() {
        return addressSave;
    }

    /**
     * @return 更新地址url
     */
    public static String getAddressUpdate() {
        return addressUpdate;
    }

    /**
     * @return 用户注册url
     */
    public static String getUserRegister() {
        return userRegister;
    }

    /**
     * 获取商家列表的分页信息url
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 获取商家列表的分页信息url
     */
    public static String getShopList(int pageNum, int pageSize) {return String.format(shopList, pageNum, pageSize);}

    /**
     * @return 保存订单的url
     */
    public static String getOrderSave() {
        return serverUrl + "/" + orderSave;
    }

    /**
     * @return 获取订单列表url
     */
    public static String getOrderList() {
        return orderList;
    }

    /**
     * @param id 订单ID
     * @return 订单详情的url
     */
    public static String getOrderDetail(int id) {
        return String.format(orderDetail, id);
    }

    /**
     * @param id 店铺id
     * @return 店铺信息和商品信息的url
     */
    public static String getShopAndGoods(int id) {
        return String.format(shopAndGoods, id);
    }
}
