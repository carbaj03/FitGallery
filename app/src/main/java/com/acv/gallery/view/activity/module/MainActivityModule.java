package com.acv.gallery.view.activity.module;

import com.acv.gallery.view.activity.ActivityScope;
import com.acv.gallery.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides @ActivityScope
    MainActivity provideHomeActivity() {
        return mainActivity;
    }
}