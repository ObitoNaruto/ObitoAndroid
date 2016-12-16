/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package android.mobile.widget.ui.BannerView;

import android.app.Activity;
import android.content.Context;
import android.mobile.widget.R;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BannerViewActivity extends Activity{

    private IndicatorBannerView mIndicatorBannerView;
    private List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);
        initImageLoader(getApplicationContext());

        mIndicatorBannerView = (IndicatorBannerView) findViewById(R.id.card_intro_banner);

        mockImageUrls();

        mIndicatorBannerView.updateData(mImageUrls);
    }

    private void mockImageUrls(){
        mImageUrls = new ArrayList<>();
        mImageUrls.add("http://imgsrc.baidu.com/baike/pic/item/a50f4bfbfbedab64d9546f70f436afc379311e5f.jpg");
        mImageUrls.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1207/26/c0/12556454_1343287284238.jpg");
//        mImageUrls.add("http://a.hiphotos.baidu.com/zhidao/pic/item/f9dcd100baa1cd11aa2ca018bf12c8fcc3ce2d74.jpg");
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(80 * 1024 * 1024); // 80 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build());
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
