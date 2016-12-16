/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.widget.ui.TabAndViewPagerPage;

import android.mobile.widget.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;

public class TabAndViewPagerPageActivity extends FragmentActivity{

    public static final String TRADE_RECORD_CONSUME = "consume";

    public static final String TRADE_RECORD_RECHARGE = "recharge";

    public static final int TAB_ITEM_CONSUME = 0;

    public static final int TAB_ITEM_RECHARGE = 1;

    private static int OFF_SCREEN_PAGE_LIMIT = 2;

    private ScrollingTabContainer mScrollingTabBar;

    private ViewPager mViewPager;

    private TabPagerAdapter mPagerAdapter;

    private ArrayList<ScrollingTabContainer.TabItemData> mTabsInfo = new ArrayList<ScrollingTabContainer.TabItemData>();

    private String mDefaultTradeType = TRADE_RECORD_RECHARGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_and_viewpager_page);

        initTabData();
        initView();
    }

    private void initTabData() {
        ScrollingTabContainer.TabItemData rechargeItemData = new ScrollingTabContainer.TabItemData();
        rechargeItemData.mId = TAB_ITEM_RECHARGE;
        rechargeItemData.mTitle = "充值";
        rechargeItemData.mTag = TRADE_RECORD_RECHARGE;
        mTabsInfo.add(rechargeItemData);
        ScrollingTabContainer.TabItemData consumeItemData = new ScrollingTabContainer.TabItemData();
        consumeItemData.mId = TAB_ITEM_CONSUME;
        consumeItemData.mTitle = "消费";
        consumeItemData.mTag = TRADE_RECORD_CONSUME;
        mTabsInfo.add(consumeItemData);

        ScrollingTabContainer.TabItemData test1 = new ScrollingTabContainer.TabItemData();
        test1.mId = 2;
        test1.mTitle = "test1";
        test1.mTag = "test1";
        mTabsInfo.add(test1);

        ScrollingTabContainer.TabItemData test2 = new ScrollingTabContainer.TabItemData();
        test2.mId = 3;
        test2.mTitle = "test2";
        test2.mTag = "test2";
        mTabsInfo.add(test2);

        ScrollingTabContainer.TabItemData test3 = new ScrollingTabContainer.TabItemData();
        test3.mId = 4;
        test3.mTitle = "test3";
        test3.mTag = "test3";
        mTabsInfo.add(test3);
    }

    private void initView() {
        mScrollingTabBar = (ScrollingTabContainer) findViewById(R.id.tab_bar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(OFF_SCREEN_PAGE_LIMIT);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setOnPageChangeListener(mPageChangeListener);
        if (mViewPager.getAdapter() == null) {
            mViewPager.setAdapter(mPagerAdapter);
        }
        mScrollingTabBar.setTabClickListener(mTabClickListener);
        addTabs();
    }

    private void addTabs() {
        if (null == mTabsInfo || mTabsInfo.isEmpty()) {
            return;
        }
        mScrollingTabBar.addTabs(mTabsInfo, 0);
        mPagerAdapter.notifyDataSetChanged();
        setDefaultTab(mDefaultTradeType);//这个类型就是数据bean中的tag
    }

    private void setDefaultTab(String tradeType) {
        int index = mScrollingTabBar.setTabSelectedByTag(tradeType);
        mViewPager.setCurrentItem(index);
    }

    private class TabPagerAdapter extends FragmentPagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < mTabsInfo.size()) {
                ScrollingTabContainer.TabItemData tabData = mTabsInfo.get(position);
                String tag = tabData.mTag;
                Fragment fragment = null;
                if (TextUtils.equals(tag, TRADE_RECORD_CONSUME)) {
                    fragment = TabFragment.getInstance("消费fragment");
                } else if (TextUtils.equals(tag, TRADE_RECORD_RECHARGE)) {
                    fragment = TabFragment.getInstance("充值fragment");
                }else if(TextUtils.equals(tag, "test1")){
                    fragment = TabFragment.getInstance("test1 fragment");
                }else if(TextUtils.equals(tag, "test2")){
                    fragment = TabFragment.getInstance("test2 fragment");
                }else if(TextUtils.equals(tag, "test3")){
                    fragment = TabFragment.getInstance("test3 fragment");
                }
                return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return mTabsInfo.size();
        }
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            if (position < 0 || position >= mTabsInfo.size()) {
                return;
            }
            mScrollingTabBar.setTabSelected(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //to do nothing
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //to do nothing
        }
    };

    private ScrollingTabContainer.OnTabClickListener mTabClickListener
            = new ScrollingTabContainer.OnTabClickListener() {
        @Override
        public void onTabClicked(int position) {
            mViewPager.setCurrentItem(position);
        }
    };
}
