/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.widget.ui.toast;

import android.app.Activity;
import android.os.Bundle;

public class WealthToastAcitivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        WealthToast.getInstance().makeSuccess(this, "成功");

        WealthToast.getInstance().makeFavorite(this, "失败");

    }
}
