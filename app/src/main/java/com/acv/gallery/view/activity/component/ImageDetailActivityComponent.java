package com.acv.gallery.view.activity.component;

import com.acv.gallery.view.activity.ActivityScope;
import com.acv.gallery.view.activity.ImageDetailActivity;
import com.acv.gallery.view.activity.MainActivity;
import com.acv.gallery.view.activity.module.ImageDetailModule;
import com.acv.gallery.view.activity.module.MainActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = ImageDetailModule.class
)
public interface ImageDetailActivityComponent {

    ImageDetailActivity inject(ImageDetailActivity homeActivity);

}