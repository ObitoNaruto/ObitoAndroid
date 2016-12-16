package android.mobile.widget.ui.BannerView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mPageChangeListener;
    private List<String> mItemInfoList;
    private SparseArray<ImageView> mImageViewCache = new SparseArray<ImageView>();
    private Handler mHandler;

    private ViewPager.OnPageChangeListener mInnerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            int index = position;
            if (getCount() > 1) {
                if (position == 0) {
                    index = getCount() - 2;
                } else if (position == getCount() - 1) {
                    index = 1;
                }
                if (position != index) {
                    final int i = index;
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(i, false);
                        }
                    }, 500); // 延迟0.5秒滑动动画结束后再setCurrentItem，否则切换的很突兀
                }
            }
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageSelected((index - 1) % getItemCount());
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mPageChangeListener != null) {
                mPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    public ImagePagerAdapter(Context context) {
        this.mContext = context;
        this.mHandler = new Handler();
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(mInnerPageChangeListener);
        mViewPager.setAdapter(this);
        mViewPager.setCurrentItem(1);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mPageChangeListener = listener;
    }

    public void updateData(List<String> itemList) {
        mItemInfoList = itemList;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return mItemInfoList == null ? 0 : mItemInfoList.size();
    }

    @Override
    public int getCount() {
        return mItemInfoList == null ? 0 : (mItemInfoList.size() > 1 ? mItemInfoList.size() + 2 : mItemInfoList.size());
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = getViewPagerItem(position);
        container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public boolean hasActionMenu(int position) {
        return false;
    }

    private int getItemIndex(int viewPagerPosition) {
        int itemCount = getItemCount();
        if (itemCount < 2) {
            return viewPagerPosition;
        }
        if (viewPagerPosition == 0) {
            return itemCount - 1;
        } else if (viewPagerPosition == getCount() - 1) {
            return 0;
        } else {
            return viewPagerPosition - 1;
        }
    }

    public void release() {
        for (int i = 0; i < mImageViewCache.size(); i++) {
            ImageView imageSwitcher = mImageViewCache.get(i);
            if (imageSwitcher != null && imageSwitcher.getParent() != null) {
                ((ViewGroup) imageSwitcher.getParent()).removeView(imageSwitcher);
            }
        }
        mImageViewCache.clear();
    }

    private ImageView getViewPagerItem(int pos) {
        final ImageView imageView;
        ImageView cachedImageView = mImageViewCache.get(pos);
        final int position = getItemIndex(pos);
        if (cachedImageView == null) {
            cachedImageView = new ImageView(mContext);
            imageView = cachedImageView;
            final String imageUrl = mItemInfoList.get(position);
            ImageLoader.getInstance().displayImage(imageUrl, imageView);
            mImageViewCache.put(pos, imageView);
        }
        return cachedImageView;
    }

}
