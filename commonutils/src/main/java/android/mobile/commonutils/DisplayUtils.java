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

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;

public class DisplayUtils {
    /**
     * 获取密度
     * @param context
     * @return
     */
    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;
        return density;
    }
    /**
     * px to dp
     * @param context
     * @param pxValue
     * @return
     */
    public static float pxTodp(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue /scale + 0.5f);
    }

    /**
     * dp to px
     * @param context
     * @param dipVlaue
     * @return
     */
    public static float dipToPx(Context context, float dipVlaue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipVlaue * scale + 0.5f);
    }

    /**
     * dp to px
     * @param context
     * @param dipVlaue
     * @return
     */
    public static float dipToPx2(Context context, float dipVlaue){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dipVlaue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px to sp
     * @param context
     * @param pxVlaue
     * @return
     */
    public static float pxToSp(Context context, float pxVlaue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxVlaue / fontScale + 0.5f);
    }

    /**
     * sp to px
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }

    /**
     * sp to px
     * @param context
     * @param spValue
     * @return
     */
    public static float spToPx2(Context context, float spValue){
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spValue,
                context.getResources().getDisplayMetrics() );
    }

    /**
     * 获取屏幕信息
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * 打印屏幕信息
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  屏幕信息:  ");
        sb.append("\ndensity         :").append(dm.density);
        sb.append("\ndensityDpi      :").append(dm.densityDpi);
        sb.append("\nheightPixels    :").append(dm.heightPixels);
        sb.append("\nwidthPixels     :").append(dm.widthPixels);
        sb.append("\nscaledDensity   :").append(dm.scaledDensity);
        sb.append("\nxdpi            :").append(dm.xdpi);
        sb.append("\nydpi            :").append(dm.ydpi);
        return dm;
    }

    /**
     *屏幕宽度
     * @param activity Activity
     */
    public static int getDialogW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return w;
    }

    /**
     * 屏幕宽度
     *
     * @param activity Activity
     */
    public static int getScreenW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
        return w;
    }

    /**
     *屏幕高度
     * @param activity Activity
     */
    public static int getScreenH(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }

    /**
     * Toggle keyboard If the keyboard is visible,then hidden it,if it's
     * invisible,then show it
     *
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
