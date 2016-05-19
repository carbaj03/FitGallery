package com.acv.gallery.view.fragment.module;

import com.acv.gallery.presenter.GalleryPresenter;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.GalleryView;
import com.acv.gallery.view.adapter.GalleryAdapter;
import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.presenter.GalleryPresenterImpl;
import com.acv.gallery.util.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class GalleryViewModule {

    private GalleryView galleryView;

    public GalleryViewModule(GalleryView galleryView) {
        this.galleryView = galleryView;
    }

    @Provides @FragmentScope
    GalleryView provideGalleryView() {
        return galleryView;
    }

    @Provides @FragmentScope
    GalleryPresenter provideCharacterListFragmentPresenter(GalleryRepository dataSource) {
        return new GalleryPresenterImpl(galleryView, dataSource);
    }

    @Provides @FragmentScope
    GalleryAdapter galleryAdapter(ImageLoader imageLoader) {
        return new GalleryAdapter(imageLoader);
    }
}