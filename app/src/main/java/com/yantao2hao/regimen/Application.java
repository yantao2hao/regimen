package com.yantao2hao.regimen;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class Application extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp(){

        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "regimen/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);//全局初始化此配置
    }
}
