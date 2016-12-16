/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.widget.ui.TabAndViewPagerPage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabFragment extends Fragment {
    private static String mTag;
    private static TabFragment mTabFragment;

    public TabFragment(){
    }

    public static TabFragment getInstance(String tag){
        mTag = tag;
        mTabFragment = new TabFragment();
        return mTabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setText(mTag);
        return tv;
    }
}
