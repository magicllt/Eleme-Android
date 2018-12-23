package com.example.zxq.elework.utils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * JavaBean的工具类
 * 功能包括: 对象转map，map转对象，对象的属性复制，对象json化
 */
public class BeanUtil {
    /**
     * 实体对象转成Map
     * @param obj 实体对象
     * @return obj对应的map, key为obj的成员名字，value为该成员的属性值
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 实体对象转map，但是属性值为null的属性不包括在map中
     * @param obj 要转化的对象
     * @return 转化成功的map
     */
    public static Map<String, Object> object2MapWithoutNull(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object val = field.get(obj);
                if (val != null){
                    map.put(field.getName(), field.get(obj));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return 转化的对象
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 对象转化为json
     * @param obj 对象
     * @return obj对应的json字符串
     */
    public static String object2Json(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 对象的属性复制，功能类似于Spring框架的BeanUtils.copyProperty()
     * 将A中的同名属性赋值给B中的同名属性
     * @param modelA 源对象
     * @param bClass 要转化的目标对象类
     * @param <A> 源对象的类
     * @param <T> 目标对象的类
     * @return 转化好的目标对象
     */
    public static <A, T> T modelAconvertoB(A modelA, Class<T> bClass) {
        /// 利用Gson完成转化的功能
        try {
            Gson gson = new Gson();
            String gsonA = gson.toJson(modelA);
            T instanceB = gson.fromJson(gsonA, bClass);
            return instanceB;
        } catch (Exception e) {
            return null;
        }
    }
}

