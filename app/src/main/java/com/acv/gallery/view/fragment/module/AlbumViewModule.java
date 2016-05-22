package com.acv.gallery.view.fragment.module;

import com.acv.gallery.presenter.AlbumPresenter;
import com.acv.gallery.presenter.AlbumPresenterImpl;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.AlbumView;
import com.acv.gallery.view.adapter.AlbumAdapter;
import com.acv.gallery.view.adapter.BaseAdapter;
import com.acv.gallery.view.fragment.AlbumListFragment;
import com.acv.gallery.view.fragment.FragmentScope;
import com.acv.gallery.util.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumViewModule {

    private AlbumView albumView;

    public AlbumViewModule(AlbumView albumView) {
        this.albumView = albumView;
    }

    @Provides @FragmentScope
    AlbumView provideAlbumView() {
        return albumView;
    }

    @Provides @FragmentScope
    AlbumPresenter provideAlbumPresenter(GalleryRepository dataSource) {
        return new AlbumPresenterImpl(albumView, dataSource);
    }

    @Provides @FragmentScope
    AlbumAdapter albumAdapter(ImageLoader imageLoader) {
        return new AlbumAdapter(imageLoader);
    }
}