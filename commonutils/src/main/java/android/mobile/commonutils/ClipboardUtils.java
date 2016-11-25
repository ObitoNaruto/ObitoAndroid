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
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

public class ClipboardUtils {
    public static void copyToClipboardSupport(Context context, String text) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(text);
    }

    public static void getLatestTextSupport(Context context) {
        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.getText();
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getLatestText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int getItemCount(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = clipboard.getPrimaryClip();
        return data.getItemCount();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String getText(Context context, int index) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > index) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }
}
