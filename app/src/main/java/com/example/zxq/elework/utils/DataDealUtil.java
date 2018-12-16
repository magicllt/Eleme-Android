package com.example.zxq.elework.utils;

/**
 * Created by LLT on 2018/12/13.
 */

public class DataDealUtil {

    static public String dealTelephone(String phone){
        StringBuilder builder = new StringBuilder();
        builder.append(phone.substring(0, 3));
        builder.append("****");
        builder.append(phone.substring(7));
        return builder.toString();
    }
}
