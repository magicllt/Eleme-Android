package com.example.zxq.elework.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LLT on 2018/12/16.
 * 活动的控制器，管理活动
 */

public class ActivityController {

    static private List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加活动到控制器
     * @param activity
     */
    static public void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 在控制器删除活动
     * @param activity
     */
    static public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 结束全部的活动
     */
    static public void finishAll(){
        for (Activity activity: activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 移除处了特定活动以外的其他活动
     * @param keepActivity
     */
    static public void finishOther(Activity keepActivity){
        for (Activity activity: activities){
            if (!activity.isFinishing() && activity != keepActivity){
                activity.finish();
            }
        }
    }

}
