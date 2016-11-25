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
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.List;

public class SmsUtils {
    /**
     * 发送短信
     * @param number 手机号码
     * @param content 短信内容
     * need permission:<uses-permission android:name="android.permission.SEND_SMS"/>
     */
    public static void sendTextMessage(String number, String content){
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> contents = smsManager.divideMessage(content);
        for(String str : contents){
            smsManager.sendTextMessage(number, null, str, null, null);
        }
    }

    /**读取系统短信
     * @param context
     * need permission: <uses-permission android:name="android.permission.READ_SMS"/>
     */
    public static void readSystemSms(Context context){
        Uri uri =  Uri.parse("content://sms/");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        List<SmsInfo> smsInfos = new ArrayList<>();
        while(cursor.moveToNext())
        {
            String address = cursor.getString(0);
            long date = cursor.getLong(1);
            int type = cursor.getInt(2);
            String body = cursor.getString(3);

            SmsInfo smsInfo = new SmsInfo(date, type, body, address);
            smsInfos.add(smsInfo);
        }
        cursor.close();
    }

    static class SmsInfo
    {
        private long date;
        private int type;
        private String body;
        private String address;
        private int id;

        public SmsInfo()
        {

        }


        public SmsInfo(long date, int type, String body, String address, int id)
        {
            this.date = date;
            this.type = type;
            this.body = body;
            this.address = address;
            this.id = id;
        }
        public SmsInfo(long date, int type, String body, String address)
        {
            this.date = date;
            this.type = type;
            this.body = body;
            this.address = address;
        }

        public long getDate()
        {
            return date;
        }
        public void setDate(long date)
        {
            this.date = date;
        }
        public int getType()
        {
            return type;
        }
        public void setType(int type)
        {
            this.type = type;
        }
        public String getBody()
        {
            return body;
        }
        public void setBody(String body)
        {
            this.body = body;
        }
        public String getAddress()
        {
            return address;
        }
        public void setAddress(String address)
        {
            this.address = address;
        }


        public int getId()
        {
            return id;
        }


        public void setId(int id)
        {
            this.id = id;
        }


    }


    /**
     * 生成一条短信
     * @param context
     * need permission:<uses-permission android:name="android.permission.WRITE_SMS"/>
     */
    public static void insertSystemSms(Context context){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://sms");
        ContentValues values = new ContentValues();
        values.put("address", "15833227282");
        values.put("type", 1);
        values.put("date", System.currentTimeMillis());
        values.put("body", "牛逼！测试一下汉字短信乱码不？");
        resolver.insert(uri, values);
    }
}
