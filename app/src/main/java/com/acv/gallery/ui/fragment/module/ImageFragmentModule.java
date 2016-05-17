package com.acv.gallery.ui.fragment.module;

import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.adapter.AlbumAdapter;
import com.acv.gallery.ui.fragment.AlbumListFragment;
import com.acv.gallery.ui.fragment.FragmentScope;
import com.acv.gallery.ui.fragment.ImageFragment;
import com.acv.gallery.ui.fragment.presenter.AlbumListFragmentPresenter;
import com.acv.gallery.ui.fragment.presenter.ImageFragmentPresenter;
import com.acv.gallery.util.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageFragmentModule {

    private ImageFragment imageFragment;

    public ImageFragmentModule(ImageFragment imageFragment) {
        this.imageFragment = imageFragment;
    }

    @Provides @FragmentScope
    ImageFragment provideImageListFragment() {
        return imageFragment;
    }

    @Provides @FragmentScope
    ImageFragmentPresenter provideImageFragmentPresenter(DataSource dataSource) {
        return new ImageFragmentPresenter(imageFragment, dataSource);
    }

}