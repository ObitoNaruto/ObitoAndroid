package android.mobile.widget.ui.BannerView;

import android.content.Context;
import android.mobile.widget.R;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.List;

public class IndicatorBannerView extends FrameLayout {
    private static final int DEFAULT_SCROLL_INTERVAL = 5000;

    private ViewPager mViewPager;
    private ViewPagerIndicatorBar mViewPagerIndicatorBar;

    private int mScrollInterval; // 滚动时间间隔
    private ImagePagerAdapter mPagerAdapter;
    private Handler mHandler;

    private Runnable mScrollToNextPageRunnable = new Runnable() {
        @Override
        public void run() {
            int currIndex = mViewPager.getCurrentItem();
            int totalPage = mPagerAdapter.getCount();
            int target = (currIndex + 1) % totalPage;
            mViewPager.setCurrentItem(target);
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // do nothing
        }

        @Override
        public void onPageSelected(int position) {
            // 更新indicator
            mViewPagerIndicatorBar.setSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                // 用户手动拖动时停止自动滚动
                stopAutoScrollViewPager();
            } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                // 当状态恢复为idle时开始自动滚动
                startAutoScrollViewPager();
            }
        }
    };

    public IndicatorBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void updateData(List<String> itemList) {
        if (mPagerAdapter == null) {
            mPagerAdapter = new ImagePagerAdapter(getContext());
        }
        mViewPagerIndicatorBar.setIndicatorNum(itemList == null ? 0 : itemList.size());
        mPagerAdapter.setOnPageChangeListener(mOnPageChangeListener);
        mPagerAdapter.updateData(itemList);
        mPagerAdapter.setViewPager(mViewPager);
        stopAutoScrollViewPager();
        startAutoScrollViewPager();
    }

    public void setScrollInterval(int intervalInMillis) {
        this.mScrollInterval = intervalInMillis;
    }

    private void init() {
        setScrollInterval(DEFAULT_SCROLL_INTERVAL);
        mHandler = new Handler();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPagerIndicatorBar = (ViewPagerIndicatorBar) findViewById(R.id.indicatorBar);
    }

    private void startAutoScrollViewPager() {
        int totalPage = mPagerAdapter.getCount();
        if (totalPage > 1) {
            // 只有数量大于1时才会滚动切换
            mHandler.postDelayed(mScrollToNextPageRunnable, mScrollInterval);
        }
    }

    private void stopAutoScrollViewPager() {
        int totalPage = mPagerAdapter.getCount();
        if (totalPage > 1) {
            mHandler.removeCallbacks(mScrollToNextPageRunnable);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mPagerAdapter != null) {
            mPagerAdapter.release();
        }
        super.onDetachedFromWindow();
    }

}
