package com.acv.gallery;

import com.acv.gallery.ui.activity.component.MainActivityComponent;
import com.acv.gallery.ui.activity.module.MainActivityModule;
import com.acv.gallery.ui.fragment.component.AlbumListFragmentComponent;
import com.acv.gallery.ui.fragment.component.GalleryListFragmentComponent;
import com.acv.gallery.ui.fragment.component.ImageFragmentComponent;
import com.acv.gallery.ui.fragment.module.AlbumListFragmentModule;
import com.acv.gallery.ui.fragment.module.GalleryListFragmentModule;
import com.acv.gallery.ui.fragment.module.ImageFragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    MainActivityComponent plus(MainActivityModule module);

    GalleryListFragmentComponent plus(GalleryListFragmentModule module);

    AlbumListFragmentComponent plus(AlbumListFragmentModule module);

    ImageFragmentComponent plus(ImageFragmentModule module);

}