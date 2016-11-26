/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.service.log.mi;

import android.util.Log;

/**
 * 好处就是统一日志查询，方便开发筛选日志
 */
public class LogUtils {

    // need to run "adb shell setprop log.tag.TSMClient DEBUG"
    public final static String TAG = "projectName";

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(TAG + "." + tag, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String msg, Exception ex) {
        Log.e(TAG, msg, ex);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(TAG + "." + tag, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(TAG + "." + tag, msg);
    }

    public static void v(String msg) {
        Log.d(TAG, msg);
    }

}
