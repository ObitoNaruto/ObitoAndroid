/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.commonutils;

import android.app.ProgressDialog;
import android.content.Context;

public class ViewProgressDialogUtils {

    public static void showProgressDialog(Context context, ProgressDialog progressDialog, int msgId) {
        showProgressDialog(progressDialog, context.getString(msgId));
    }

    public static void showProgressDialog(ProgressDialog progressDialog, String msg) {
        if (progressDialog == null) {
            return;
        }
        progressDialog.setMessage(msg);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public static void dismissProgressDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
