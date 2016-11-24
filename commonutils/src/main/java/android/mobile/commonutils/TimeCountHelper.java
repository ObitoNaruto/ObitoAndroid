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

import android.os.CountDownTimer;

/**
 *常用于时间倒数程序
 */
public class TimeCountHelper extends CountDownTimer {

    public static final long TOTAL_TIME = 60 * 1000;

    public static final long INTERVAL = 1000;

    public interface OnTimeCountListener {

        void onTick(long secondUntilFinished);

        void onFinish();
    }

    private OnTimeCountListener mOnTimeCountListener;

    public void setOnTimeCountListener(OnTimeCountListener onTimeCountListener) {
        mOnTimeCountListener = onTimeCountListener;
    }

    public TimeCountHelper(long millisInFuture, long countDownInterval, OnTimeCountListener onTimeCountListener) {
        super(millisInFuture, countDownInterval);
        this.mOnTimeCountListener = onTimeCountListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long secondUntilFinished = millisUntilFinished / 1000;
        if (null != mOnTimeCountListener) {
            mOnTimeCountListener.onTick(secondUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (null != mOnTimeCountListener) {
            mOnTimeCountListener.onFinish();
        }
    }

}

