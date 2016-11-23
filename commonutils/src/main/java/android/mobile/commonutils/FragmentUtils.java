/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.commonutils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

public class FragmentUtils {

    /**
     * Replace an existing fragment that was added to a container with the default fragment animation.
     * @param f The new fragment to place in the container.
     */
    public static void replaceFragment(Activity activity, Fragment f) {
        replaceFragment(activity, f, false, false, true);
    }

    public static void replaceFragment(Activity activity, Fragment f, boolean hasAnim) {
        replaceFragment(activity, f, false, false, hasAnim);
    }

    public static void replaceFragment(Activity activity, Fragment f, boolean popupTop,
            boolean addToBackStack) {
        replaceFragment(activity, f, popupTop, addToBackStack, true);
    }

    public static void replaceFragment(Activity activity, Fragment f,
            boolean popupTop, boolean addToBackStack, boolean hasAnim) {
        if (activity == null) {
            return;
        }
        FragmentManager fm = activity.getFragmentManager();
        if (popupTop) {
            fm.popBackStack();
        }

        FragmentTransaction transaction = fm.beginTransaction();
        if (hasAnim) {
            transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit, R.animator.fragment_slide_right_enter,
                    R.animator.fragment_slide_right_exit);
        }

        transaction.replace(android.R.id.content, f, f.getClass().getName());
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 清理fragment栈。
     *
     * @param activity activity对象，不能未空。
     */
    public static void clearFragmentBackStack(Activity activity) {
        if (activity == null) {
            LogUtils.w("clearFragmentBackStack() called!But param activity is null!");
            return;
        }
        FragmentManager manager = activity.getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * 当前fragment是否处在活动中
     * @param fragment
     * @return
     */
    public static boolean isFragmentValid(Fragment fragment) {
        return fragment != null && fragment.getActivity() != null && !fragment.getActivity().isFinishing() && !fragment.isRemoving() && !fragment.isDetached();
    }
}
