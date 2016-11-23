/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.commonutils;

import android.content.Context;
import android.widget.Toast;

public class ViewToastUtils {

    public static void showToast(Context context, int descRes) {
        showToast(context, context.getString(descRes));
    }

    public static void showToast(Context context, String descRes) {
        Toast.makeText(context, descRes, Toast.LENGTH_LONG).show();
    }

}
