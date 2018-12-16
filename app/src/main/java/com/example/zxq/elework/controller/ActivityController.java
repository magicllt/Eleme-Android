package com.example.zxq.elework.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LLT on 2018/12/16.
 */

public class ActivityController {

    static private List<Activity> activities = new ArrayList<Activity>();

    static public void addActivity(Activity activity){
        activities.add(activity);
    }

    static public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    static public void finishAll(){
        for (Activity activity: activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
    static public void finishOther(Activity keepActivity){
        for (Activity activity: activities){
            if (!activity.isFinishing() && activity != keepActivity){
                activity.finish();
            }
        }
    }

}
