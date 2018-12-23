package com.example.zxq.elework.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by LLT on 2018/11/29.
 * 检验数据有效性的工具类
 */
public class ValidatorUtil {

    ///    手机号码正则表达式
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 检验字符串是否为空
     * @param str 要检验的字符串
     * @return 布尔值 代表是否为空
     */
    public static boolean isEmpty(String str){
        return TextUtils.isEmpty(str);
    }

    /**
     * 检验手机号码是否有效
     * @param mobile 传入的手机号码
     * @return 布尔值 代表是正确的手机号码
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
