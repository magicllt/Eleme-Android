package com.example.zxq.elework.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    static public String convertToMoney(BigDecimal val){
        return "￥" + val.toString();
    }

    static public String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(date);
    }
}
