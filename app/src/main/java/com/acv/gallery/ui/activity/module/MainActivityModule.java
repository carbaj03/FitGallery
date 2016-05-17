package com.acv.gallery.ui.activity.module;

import com.acv.gallery.ui.activity.ActivityScope;
import com.acv.gallery.ui.activity.MainActivity;

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