package com.acv.gallery.presenter;

import com.acv.gallery.model.Album;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.GalleryView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GalleryPresenterImpl implements GalleryPresenter {

    private GalleryView view;
    private GalleryRepository repository;

    public GalleryPresenterImpl(GalleryView view, GalleryRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override public void loadAlbums() {
        view.displayLoading(true);
        load(repository.getAlbums());
    }

    @Override public void loadAlbumsPerDay() {
        view.displayLoading(true);
        load(repository.getAlbumsPerDay());
    }

    @Override public void loadAlbumsPerWeek() {
        view.displayLoading(true);
        load(repository.getAlbumsPerWeek());
    }

    @Override public void loadAlbumsPerMonth() {
        view.displayLoading(true);
        load(repository.getAlbumsPerMonth());
    }

    private void load(Observable<List<Album>> albums){
        albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Album>>() {
                    @Override
                    public void call(List<Album> albums) {
                        view.displayLoading(false);
                        view.displayGallery(albums);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        view.displayLoading(false);
                    }
                });
    }

}
