/**
 * created by 冀秦, Sep 12, 2016 Copyright (c) 2016, 1042407818@qq.com All Rights Reserved.
 *
 * ##################################################### #                                                   # #
 *               _oo0oo_                     # #                      o8888888o                    # #
 *    88" . "88                    # #                      (| -_- |)                    # #                      0\  =
 * /0                    # #                    ___/`---'\___                  # #                  .' \\|     |# '.
 *             # #                 / \\|||  :  |||# \                # #                / _||||| -:- |||||- \
 *   # #               |   | \\\  -  #/ |   |              # #               | \_|  ''\---/''  |_/ |             # #
 *           \  .-\__  '-'  ___/-. /             # #             ___'. .'  /--.--\  `. .'___           # #          .""
 * '<  `.___\_<|>_/___.' >' "".         # #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       # #         \  \ `_.   \_
 * __\ /__ _/   .-` /  /       # #     =====`-.____`.___ \_____/___.-`___.-'=====    # #                       `=---='
 *                   # #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   # #
 *         # #               佛祖保佑         永无BUG              # #                                                   #
 * #####################################################
 */

package android.mobile.commonutils;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    /**
     * 关闭数据流的公用方法，适用于所有implements了Closeable接口
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从Assert文件夹中取文件数据
     */
    public static boolean retrieveFileFromAssets(Context context, String fileName, String path) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            closeQuietly(is);
            closeQuietly(fos);
        }
    }
}
