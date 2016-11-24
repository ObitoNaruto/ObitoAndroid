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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NeworkUtils {

    /**
     * 网络是否连接.
     *
     * @param context 上下文,此处传base context即可
     * @return true if connnected otherwise false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * wifi是否连接.
     *
     * @param context 上下文,此处传base context即可
     * @return true if connnected otherwise false
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        boolean isWifiConnected = networkInfo != null && networkInfo.isConnected();
        if (Build.VERSION.SDK_INT < 16) {
            isWifiConnected = isWifiConnected
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } else {
            isWifiConnected = isWifiConnected && !connManager.isActiveNetworkMetered();
        }
        return isWifiConnected;
    }

    /**
     * 手机网络是否连接.
     *
     * @param context 上下文,此处传base context即可
     * @return true if connnected otherwise false
     */
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 判断当前是否为计费网络,除了一般的2G/3G为收费网络之外,wifi情况下也可能为收费网络(比如接入了一个3G手机共享的wifi热点)
     *
     * @param context 上下文,此处传base context即可
     * @return true if connnected otherwise false
     */
    public static boolean isNetworkMetered(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && connManager.isActiveNetworkMetered();
    }

    /**
     * 获取网络类型.
     *
     * @param context 上下文,此处传base context即可
     * @return true if connnected otherwise false
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            // TYPE_NONE -1
            // TYPE_MOBILE 0
            // TYPE_WIFI 1
            // TYPE_WIMAX 6
            // TYPE_BLUETOOTH
            //
            // 7
            // TYPE_ETHERNET 9
            // TYPE_USB_SHARE_NET 14
            return networkInfo.getType();
        }
        return -1;
    }

    /**
     * 获取ip地址，这个方法拿到的是ipv4的局域网ip。
     *
     * @return ipv4的地址
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            LogUtils.e("WifiPreference IpAddress", e);
        }
        return null;
    }

}
