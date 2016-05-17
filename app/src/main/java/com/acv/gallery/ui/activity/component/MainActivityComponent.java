package com.acv.gallery.ui.activity.component;

import com.acv.gallery.ui.activity.ActivityScope;
import com.acv.gallery.ui.activity.MainActivity;
import com.acv.gallery.ui.activity.module.MainActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainActivityComponent {

    MainActivity inject(MainActivity homeActivity);

}