/**
 *
 *	created by 冀秦, Sep 12, 2016
 *	Copyright (c) 2016, 1042407818@qq.com All Rights Reserved.
 *
 *                #####################################################
 *                #                                                   #
 *                #                       _oo0oo_                     #
 *                #                      o8888888o                    #
 *                #                      88" . "88                    #
 *                #                      (| -_- |)                    #
 *                #                      0\  =  /0                    #
 *                #                    ___/`---'\___                  #
 *                #                  .' \\|     |# '.                 #
 *                #                 / \\|||  :  |||# \                #
 *                #                / _||||| -:- |||||- \              #
 *                #               |   | \\\  -  #/ |   |              #
 *                #               | \_|  ''\---/''  |_/ |             #
 *                #               \  .-\__  '-'  ___/-. /             #
 *                #             ___'. .'  /--.--\  `. .'___           #
 *                #          ."" '<  `.___\_<|>_/___.' >' "".         #
 *                #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 *                #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 *                #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 *                #                       `=---='                     #
 *                #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 *                #                                                   #
 *                #               佛祖保佑         永无BUG              #
 *                #                                                   #
 *                #####################################################
 */

package android.mobile.commonutils;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;

public class PollingUtils {
    /**
     * 判断是否存在轮询服务
     *
     * @param context 上下文
     * @param cls     Class
     * @return
     */
    public static boolean isPollingServiceExist(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_NO_CREATE);
        return pendingIntent != null;
    }

    /**
     * 开启轮询服务
     *
     * @param context  上下文
     * @param interval 时间间隔，单位为秒
     * @param cls      Class
     */
    public static void startPollingService(Context context, int interval,
            Class<?> cls) {
        startPollingService(context, interval, cls, null);
    }

    /**
     * 开启轮询服务
     *
     * @param context  上下文
     * @param interval 时间间隔，单位为秒
     * @param cls      Class
     * @param action   Action
     */
    public static void startPollingService(Context context, int interval,
            Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = SystemClock.elapsedRealtime();
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                interval * 1000, pendingIntent);
    }

    /**
     * 停止轮询服务
     *
     * @param context 上下文
     * @param cls     Class
     */
    public static void stopPollingService(Context context, Class<?> cls) {
        stopPollingService(context, cls, null);
    }

    /**
     * 停止轮询服务
     *
     * @param context 上下文
     * @param cls     Class
     * @param action  Action
     */
    public static void stopPollingService(Context context, Class<?> cls,
            String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }


    //-------定时器---------------
    /**
     * 开启定时器
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void startAlarmIntent(Context context, int triggerAtMillis, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP,triggerAtMillis, pendingIntent);
    }

    /**
     * 关闭定时器
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void stopAlarmIntent(Context context, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }

    /**
     * 开启轮询服务
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void startAlarmService(Context context, int triggerAtMillis, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        startAlarmIntent(context, triggerAtMillis, pendingIntent);
    }
    /**
     * 停止启轮询服务
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void stopAlarmService(Context context, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        stopAlarmIntent(context, pendingIntent);
    }

    //-----------------------
}
