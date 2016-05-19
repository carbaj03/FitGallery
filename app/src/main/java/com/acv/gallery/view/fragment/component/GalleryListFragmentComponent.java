package com.acv.gallery.view.fragment.component;

import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.view.fragment.GalleryListFragment;
import com.acv.gallery.view.fragment.module.GalleryViewModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = GalleryViewModule.class
)
public interface GalleryListFragmentComponent {

    GalleryListFragment inject(GalleryListFragment galleryListFragment);

}