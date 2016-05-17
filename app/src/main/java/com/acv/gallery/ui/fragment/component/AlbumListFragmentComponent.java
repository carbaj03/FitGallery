package com.acv.gallery.ui.fragment.component;

import com.acv.gallery.ui.fragment.AlbumListFragment;
import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.GalleryListFragment;
import com.acv.gallery.ui.fragment.module.AlbumListFragmentModule;
import com.acv.gallery.ui.fragment.module.GalleryListFragmentModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = AlbumListFragmentModule.class
)
public interface AlbumListFragmentComponent {

    AlbumListFragment inject(AlbumListFragment albumListFragment);

}