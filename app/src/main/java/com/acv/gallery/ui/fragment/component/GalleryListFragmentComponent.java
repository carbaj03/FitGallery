package com.acv.gallery.ui.fragment.component;

import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.GalleryListFragment;
import com.acv.gallery.ui.fragment.module.GalleryListFragmentModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = GalleryListFragmentModule.class
)
public interface GalleryListFragmentComponent {

    GalleryListFragment inject(GalleryListFragment galleryListFragment);

}