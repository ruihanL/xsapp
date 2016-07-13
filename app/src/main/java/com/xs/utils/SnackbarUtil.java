package com.xs.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by linrh on 2016/5/25.
 */

public class SnackbarUtil {

  // make()中的第一个参数，可以写当前界面中的任意一个view对象。
  private static Snackbar mSnackbar;
  private static final int LONG = 1;
  private static final int SHORT = 0;
  public static void show(View view, String msg, int flag) {
    if (flag == 0) { // 短时显示
      mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
    } else { // 长时显示
      mSnackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
    }
    mSnackbar.show();
  }
}
