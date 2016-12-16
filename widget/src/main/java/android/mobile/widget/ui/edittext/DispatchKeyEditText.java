/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.widget.ui.edittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * 用途：要求软键盘和包裹当前editextView的容器dialog型activity一起退出
 */
public class DispatchKeyEditText extends EditText{

    public DispatchKeyEditText(Context context) {
        super(context);
    }

    public DispatchKeyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchKeyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DispatchKeyEditText(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && mOnBackPressedListener != null) {
            mOnBackPressedListener.onBackPressed();
            return true;
        } else {
            return super.dispatchKeyEventPreIme(event);
        }
    }

    private OnBackPressedListener mOnBackPressedListener;

    public void setOnBackPressedListener(
            OnBackPressedListener onBackPressedListener) {
        mOnBackPressedListener = onBackPressedListener;
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
}
