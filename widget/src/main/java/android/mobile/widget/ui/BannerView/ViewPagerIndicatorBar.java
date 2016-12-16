package android.mobile.widget.ui.BannerView;

import android.content.Context;
import android.mobile.widget.R;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewPagerIndicatorBar extends LinearLayout {
    private int mItemDimen;
    private int mItemInterval;
    private Context mContext;
    private int mTotalIndicatorNum;

    public ViewPagerIndicatorBar(Context context) {
        this(context, null);
    }

    public ViewPagerIndicatorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initialize();
    }

    private void initialize() {
        mItemDimen = getResources().getDimensionPixelSize(R.dimen.viewpager_indicator_item_dimen);
        mItemInterval = getResources().getDimensionPixelSize(R.dimen.viewpager_indicator_item_interval);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setIndicatorNum(int total) {
        setIndicatorNum(total, 0);
    }

    public void setIndicatorNum(int total, int selectIndex) {
        int index = selectIndex;
        if (total <= 0) {
            removeAllViews();
            return;
        }
        mTotalIndicatorNum = total;
        if (index <= 0) {
            index = 0;
        } else if (index >= total) {
            index = total - 1;
        }
        removeAllViews();
        LayoutParams lp = new LayoutParams(mItemDimen, mItemDimen, 1);
        lp.setMargins(mItemInterval, 0, mItemInterval, 0);
        for (int i = 0; i < total; i++) {
            ImageView indicator = new ImageView(mContext);
            indicator.setImageResource(R.drawable.viewpager_indicator_item_bg_shape_selector);
            indicator.setSelected(i == index);
            addView(indicator, lp);
        }

        if(total <= 1){
            removeAllViews();
        }
    }

    public void setSelected(int index) {
        if (index < 0 || index >= mTotalIndicatorNum) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            ImageView indicator = (ImageView) getChildAt(i);
            indicator.setSelected(i == index);
        }
    }
}
