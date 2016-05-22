package com.acv.gallery;

import com.acv.gallery.view.activity.component.ImageDetailActivityComponent;
import com.acv.gallery.view.activity.component.MainActivityComponent;
import com.acv.gallery.view.activity.module.ImageDetailModule;
import com.acv.gallery.view.activity.module.MainActivityModule;
import com.acv.gallery.view.fragment.component.AlbumListFragmentComponent;
import com.acv.gallery.view.fragment.component.GalleryListFragmentComponent;
import com.acv.gallery.view.fragment.component.PhotoFragmentComponent;
import com.acv.gallery.view.fragment.module.AlbumViewModule;
import com.acv.gallery.view.fragment.module.GalleryViewModule;
import com.acv.gallery.view.fragment.module.PhotoViewModule;

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

    ImageDetailActivityComponent plus(ImageDetailModule module);

    GalleryListFragmentComponent plus(GalleryViewModule module);

    AlbumListFragmentComponent plus(AlbumViewModule module);

    PhotoFragmentComponent plus(PhotoViewModule module);

}