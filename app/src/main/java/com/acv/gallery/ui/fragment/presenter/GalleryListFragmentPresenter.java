package com.acv.gallery.ui.fragment.presenter;

import com.acv.gallery.Album;
import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.fragment.GalleryListFragment;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GalleryListFragmentPresenter {

    private GalleryListFragment characterListFragment;
    private DataSource dataSource;

    public GalleryListFragmentPresenter(GalleryListFragment galleryListFragment, DataSource dataSource) {
        this.characterListFragment = galleryListFragment;
        this.dataSource = dataSource;
    }

    public void loadImages() {
        characterListFragment.showLoading(true);
        load(dataSource.getAlbums());
    }

    public void loadImagesPerDay() {
        characterListFragment.showLoading(true);
        load(dataSource.getAlbumsPerDay());
    }

    public void loadImagesPerWeek() {
        characterListFragment.showLoading(true);
        load(dataSource.getAlbumsPerWeek());
    }

    public void loadImagesPerMonth() {
        characterListFragment.showLoading(true);
        load(dataSource.getAlbumsPerMonth());
    }

    private void load(Observable<List<Album>> albums){
        albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Album>>() {
                    @Override
                    public void call(List<Album> images) {
                        characterListFragment.showLoading(false);
                        characterListFragment.setImages(images);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        characterListFragment.showLoading(false);
                    }
                });
    }

}
