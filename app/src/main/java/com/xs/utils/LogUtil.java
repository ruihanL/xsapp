package com.xs.utils;

import android.util.Log;

/**
 * Created by linrh on 2016/6/23.
 */

public class LogUtil {
    public static String TAG = "app";
    public static void setTag(String tag){
        TAG = tag;
    }
    public static  void show(String message)
    {
        Log.e(TAG,message);
    }
}
