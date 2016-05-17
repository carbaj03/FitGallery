package com.acv.gallery.ui.fragment.module;

import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.adapter.GalleryAdapter;
import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.GalleryListFragment;
import com.acv.gallery.ui.fragment.presenter.GalleryListFragmentPresenter;
import com.acv.gallery.util.FileUtil;
import com.acv.gallery.util.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class GalleryListFragmentModule {

    private GalleryListFragment galleryListFragment;

    public GalleryListFragmentModule(GalleryListFragment galleryListFragment) {
        this.galleryListFragment = galleryListFragment;
    }

    @Provides @FragmentScope
    GalleryListFragment provideGalleryListFragment() {
        return galleryListFragment;
    }

    @Provides @FragmentScope
    GalleryListFragmentPresenter provideCharacterListFragmentPresenter(DataSource dataSource) {
        return new GalleryListFragmentPresenter(galleryListFragment, dataSource);
    }

    @Provides @FragmentScope
    GalleryAdapter galleryAdapter(ImageLoader imageLoader) {
        return new GalleryAdapter(imageLoader);
    }
}