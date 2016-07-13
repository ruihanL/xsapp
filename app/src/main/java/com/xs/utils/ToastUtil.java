package com.xs.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by linrh on 2016/6/23.
 */

public class ToastUtil {
    public static void show(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
