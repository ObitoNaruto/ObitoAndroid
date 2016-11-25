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

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

public class ObserverHelper {
    /**
     * need permission:<uses-permission android:name="android.permission.READ_SMS"/>
     */
    public static void userContentResolver(Context context){
        //1.第一步
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        resolver.registerContentObserver(uri, true, new MyObserver(new Handler()));

        //2.第二步
        class MyObserver extends ContentObserver
        {

            public MyObserver(Handler handler)
            {
                super(handler);

            }

            //当内容观察者观察到了数据库的内容变化时调用这个方法
            //观察到消息邮箱里有一条数据库内容发生变化的通知
            @Override
            public void onChange(boolean selfChange)
            {
                super.onChange(selfChange);
//                Toast.makeText(MainActivity.this, "数据库的内容变化了", Toast.LENGTH_SHORT).show();

                ContentResolver resolver = context.getContentResolver();
                Uri uri = Uri.parse("content://sms/");
                Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
                cursor.moveToFirst();
                String address = cursor.getString(0);
                long date = cursor.getLong(1);
                int type = cursor.getInt(2);
                String body = cursor.getString(3);
                System.out.println("address = " + address + " : " + "body = " + body);
                cursor.close();

            }
        }
    }
}
