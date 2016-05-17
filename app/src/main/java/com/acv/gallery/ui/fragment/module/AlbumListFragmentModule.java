package com.acv.gallery.ui.fragment.module;

import com.acv.gallery.Album;
import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.adapter.AlbumAdapter;
import com.acv.gallery.ui.adapter.GalleryAdapter;
import com.acv.gallery.ui.fragment.AlbumListFragment;
import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.presenter.AlbumListFragmentPresenter;
import com.acv.gallery.util.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListFragmentModule {

    private AlbumListFragment albumListFragment;

    public AlbumListFragmentModule(AlbumListFragment albumListFragment) {
        this.albumListFragment = albumListFragment;
    }

    @Provides @FragmentScope
    AlbumListFragment provideAlbumListFragment() {
        return albumListFragment;
    }

    @Provides @FragmentScope
    AlbumListFragmentPresenter provideAlbumListFragmentPresenter(DataSource dataSource) {
        return new AlbumListFragmentPresenter(albumListFragment, dataSource);
    }

    @Provides @FragmentScope
    AlbumAdapter albumAdapter(ImageLoader imageLoader) {
        return new AlbumAdapter(imageLoader);
    }
}