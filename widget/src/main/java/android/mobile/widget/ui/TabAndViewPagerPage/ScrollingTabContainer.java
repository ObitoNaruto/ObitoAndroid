package android.mobile.widget.ui.TabAndViewPagerPage;

import android.content.Context;
import android.mobile.widget.R;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ScrollingTabContainer extends HorizontalScrollView {

    private LinearLayout mTabLayout;

    private Context mContext;

    private OnTabClickListener mTabClickListener;

    public interface OnTabClickListener {

        void onTabClicked(int position);
    }

    public void setTabClickListener(OnTabClickListener listener) {
        mTabClickListener = listener;
    }

    public ScrollingTabContainer(Context context) {
        this(context, null);
    }

    public ScrollingTabContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setHorizontalScrollBarEnabled(false);
        mTabLayout = new LinearLayout(context);
        mTabLayout.setMeasureWithLargestChildEnabled(true);
        mTabLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mTabLayout.setGravity(Gravity.CENTER);
        addView(mTabLayout);
    }

    public void addTab(TabItemData data, boolean isSelected) {
        if (data == null) {
            return;
        }
        TabView tabView = createTabView(data);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        tabView.setTag(data.mTag);
        if (isSelected) {
            tabView.setSelected(isSelected);
        }
    }

    public void addTab(TabItemData data, int position, boolean isSelected) {
        if (data == null) {
            return;
        }
        TabView tabView = createTabView(data);
        mTabLayout.addView(tabView, position,  new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        if (isSelected) {
            tabView.setSelected(isSelected);
        }
    }

    public void addTabs(List<TabItemData> tabs) {
        addTabs(tabs, 0);
    }

    public void addTabs(List<TabItemData> tabs, int selectedIndex) {
        if (tabs == null || tabs.isEmpty()) {
            return;
        }
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            addTab(tabs.get(i), i == selectedIndex);
        }
    }

    public void setTabSelected(int position) {
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = i == position;
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(position);
            }
        }
    }

    public int setTabSelectedByTag(String tag) {
        int selectedPosition = 0;
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            String viewTag = (String) child.getTag();
            if (TextUtils.equals(viewTag, tag)) {
                selectedPosition = i;
                break;
            }
        }
        setTabSelected(selectedPosition);
        return selectedPosition;
    }

    private void animateToTab(int position) {
        final View tabView = mTabLayout.getChildAt(position);
        Runnable tabSelector = new Runnable() {
            @Override
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                scrollTo(scrollPos, 0);
            }
        };
        post(tabSelector);
    }

    private TabView createTabView(TabItemData data) {
        TabView tabView = new TabView(mContext);
        tabView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TabView tabView = (TabView) v;
                tabView.setSelected(true);
                final int tabCount = mTabLayout.getChildCount();
                for (int i = 0; i < tabCount; i++) {
                    final View child = mTabLayout.getChildAt(i);
                    boolean isSelected = v == child;
                    child.setSelected(isSelected);
                    if (isSelected && mTabClickListener != null) {
                        mTabClickListener.onTabClicked(i);
                    }
                }
            }
        });
        tabView.setText(data.mTitle);
        return tabView;
    }

    class TabView extends TextView {

        public TabView(Context context) {
            super(context);
            setTextColor(getResources().getColorStateList(R.color.card_trade_record_tab_item_bg));
            setTextSize(TypedValue.COMPLEX_UNIT_PX,  getResources().getDimensionPixelSize(R.dimen.card_trade_record_text_size_tab_item));
            setPadding(getResources().getDimensionPixelSize(R.dimen.card_trade_record_tab_item_padding_left), 0,
                    getResources().getDimensionPixelSize(R.dimen.card_trade_record_tab_item_padding_right), 0);
            setSingleLine(true);
            setGravity(Gravity.CENTER);
        }
    }

    public static class TabItemData {
        public int mId;
        public String mTitle;
        public String mTag;
    }
}
