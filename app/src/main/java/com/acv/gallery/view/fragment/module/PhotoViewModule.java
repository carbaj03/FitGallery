package com.acv.gallery.view.fragment.module;

import com.acv.gallery.presenter.PhotoPresenter;
import com.acv.gallery.presenter.PhotoPresenterImpl;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.PhotoView;
import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.view.fragment.PhotoFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoViewModule {

    private PhotoView photoView;

    public PhotoViewModule(PhotoView photoView) {
        this.photoView = photoView;
    }

    @Provides @FragmentScope
    PhotoView providePhotoView() {
        return photoView;
    }

    @Provides @FragmentScope
    PhotoPresenter provideImageFragmentPresenter(GalleryRepository dataSource) {
        return new PhotoPresenterImpl(photoView, dataSource);
    }

}