package com.acv.gallery.view.activity.module;

import android.support.v4.view.PagerAdapter;

import com.acv.gallery.view.activity.ActivityScope;
import com.acv.gallery.view.activity.ImageDetailActivity;
import com.acv.gallery.view.activity.MainActivity;
import com.acv.gallery.view.adapter.ImagePagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDetailModule {

    private ImageDetailActivity imageDetailActivity;

    public ImageDetailModule(ImageDetailActivity imageDetailActivity) {
        this.imageDetailActivity = imageDetailActivity;
    }

    @Provides @ActivityScope
    ImageDetailActivity provideImageDetailActivity() {
        return imageDetailActivity;
    }

}