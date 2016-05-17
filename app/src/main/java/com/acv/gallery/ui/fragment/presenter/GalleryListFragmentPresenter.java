package com.acv.gallery.ui.fragment.presenter;

import com.acv.gallery.Image;
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
        load(dataSource.getImages());
    }

    public void loadImagesPerDay() {
        characterListFragment.showLoading(true);
        load(dataSource.getImagesPerDay());
    }

    public void loadImagesPerWeek() {
//        characterListFragment.showLoading(true);
//        load(dataSource.getImagesPerWeek());
    }

    public void loadImagesPerMonth() {
//        characterListFragment.showLoading(true);
//        load(dataSource.getImagesPerMonth());
    }

    private void load(Observable<List<Image>> images){
        images.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Image>>() {
                    @Override
                    public void call(List<Image> images) {
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
