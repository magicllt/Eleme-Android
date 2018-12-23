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
 * 数据处理类
 * 功能包括: 隐藏手机号的中间四位, BigDecimal加上人民币符号，格式化Date
 */
public class DataDealUtil {

    /**
     * 隐藏手机号的中间四位
     * @param phone 要处理的手机号
     * @return 处理完毕的手机号码字符串
     */
    static public String dealTelephone(String phone){
        StringBuilder builder = new StringBuilder();
        builder.append(phone.substring(0, 3));
        builder.append("****");
        builder.append(phone.substring(7));
        return builder.toString();
    }

    /**
     * 给数字添加人民币符号
     * @param val 传入的金额
     * @return 处理好的金额字符串
     */
    static public String convertToMoney(BigDecimal val){
        return "￥" + val.toString();
    }

    /**
     * 格式化date数据
     * @param date 传入的date
     * @return 格式化完毕的date字符串
     */
    static public String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        /// 手动加8小时，解决时区问题
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        date = calendar.getTime();
        return sdf.format(date);
    }
}
