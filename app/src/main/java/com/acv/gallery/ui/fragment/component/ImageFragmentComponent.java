package com.acv.gallery.ui.fragment.component;

import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.ImageFragment;
import com.acv.gallery.ui.fragment.module.ImageFragmentModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = ImageFragmentModule.class
)
public interface ImageFragmentComponent {

    ImageFragment inject(ImageFragment imageFragment);

}