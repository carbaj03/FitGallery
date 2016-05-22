package com.acv.gallery.view.fragment.component;

import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.view.fragment.ImageDetailFragment;
import com.acv.gallery.view.fragment.module.PhotoViewModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = PhotoViewModule.class
)
public interface PhotoFragmentComponent {

    ImageDetailFragment inject(ImageDetailFragment imageFragment);

}