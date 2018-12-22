package com.example.zxq.elework.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        date = calendar.getTime();
        return sdf.format(date);
    }
}
