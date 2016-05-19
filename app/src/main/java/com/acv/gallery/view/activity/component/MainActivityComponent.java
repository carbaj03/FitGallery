package com.acv.gallery.view.activity.component;

import com.acv.gallery.view.activity.ActivityScope;
import com.acv.gallery.view.activity.MainActivity;
import com.acv.gallery.view.activity.module.MainActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainActivityComponent {

    MainActivity inject(MainActivity homeActivity);

}