package com.acv.gallery.view.fragment.component;

import com.acv.gallery.view.fragment.AlbumListFragment;
import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.view.fragment.module.AlbumViewModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = AlbumViewModule.class
)
public interface AlbumListFragmentComponent {

    AlbumListFragment inject(AlbumListFragment albumListFragment);

}