package com.acv.gallery.presenter;

import com.acv.gallery.model.Album;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.GalleryView;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GalleryPresenterImpl implements GalleryPresenter {

    private GalleryView view;
    private GalleryRepository repository;
    private Subscription subscription;

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
        subscription = albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gallery -> {
                    view.displayLoading(false);
                    view.displayGallery(gallery);
                }, error -> {
                    view.displayLoading(false);
                });
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}
