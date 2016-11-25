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
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

public class MemoryUtils {
    /**
     * Get memory info of device.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi;
    }

    /**
     * Print Memory info.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo printMemoryInfo(Context context) {
        ActivityManager.MemoryInfo mi = getMemoryInfo(context);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  Memory :   ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            sb.append("\ntotalMem        :").append(mi.totalMem);
        }
        sb.append("\navailMem        :").append(mi.availMem);
        sb.append("\nlowMemory       :").append(mi.lowMemory);
        sb.append("\nthreshold       :").append(mi.threshold);
        return mi;
    }

    /**
     * Get available memory info.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getAvailMemory(Context context) {// 获取android当前可用内存大小
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

    public static String getExtMemoryInfo(Context context){
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        long totalSize = blockSize * totalBlocks;
        long avaiSize = blockSize * availableBlocks;

        String totalStr = Formatter.formatFileSize(context, totalSize);
        String avaiStr = Formatter.formatFileSize(context, avaiSize);

        return "总内存:" + totalStr + "\n" + "可用内存:" + avaiStr;
    }

    public static String getRomSpaceInfo(Context context){
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());

        long blockSize = statFs.getBlockSizeLong();
        long totalBlocks = statFs.getBlockCountLong();
        long avaiableBlocks = statFs.getAvailableBlocksLong();

        long totalSize = blockSize * totalBlocks;
        long avaiSize = blockSize * avaiableBlocks;

        String totalStr = Formatter.formatFileSize(context, totalSize);
        String avaiStr = Formatter.formatFileSize(context, avaiSize);

        return "手机内存总空间" + totalStr + "\n" + "手机内存可用空间" + avaiStr;
    }
}
