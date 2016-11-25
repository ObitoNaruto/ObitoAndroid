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

public class ContactsUtils {
    /**
     * <uses-permission android:name="android.permission.READ_CONTACTS"/>
     *<uses-permission android:name="android.permission.WRITE_CONTACTS"/>
     * @param context
     */
    public static void writeContact(Context context){
        //1.向raw_contact表中添加一条联系人信息
        ContentResolver resolver = context.getContentResolver();
        //获取raw_contact表对应的uri
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        ContentValues values = new ContentValues();
        //必须知道最后一个联系人的id
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        cursor.moveToLast();
        int lastId = cursor.getInt(0);
        int newId = lastId + 1;
        values.put("contact_id", newId);
        resolver.insert(uri, values);

        //2.使用添加的联系人id，向data表中添加数据
        ContentValues phoneValues = new ContentValues();
        phoneValues.put("data1", "123456789");
        phoneValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        phoneValues.put("raw_contact_id", newId);
        resolver.insert(dataUri, phoneValues);

        ContentValues emailValues = new ContentValues();
        emailValues.put("data1", "xxm@163.com");
        emailValues.put("mimetype", "vnd.android.cursor.item/email_v2");
        emailValues.put("raw_contact_id", newId);
        resolver.insert(dataUri, emailValues);

        ContentValues nameValues = new ContentValues();
        nameValues.put("data1", "xxm");
        nameValues.put("mimetype", "vnd.android.cursor.item/name");
        nameValues.put("raw_contact_id", newId);
        resolver.insert(dataUri, nameValues);

    }
}
