package com.xs.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by linrh on 2016/6/23.
 */

public class DialogUtil {
    public static Dialog show(Context context, String title, String message)
    {
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.setPositiveButton("确定",null);
        Dialog d = ab.create();
        d.show();
        return  d;
    }
}
