package com.acv.gallery.presenter;

import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.AlbumView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AlbumPresenterImpl implements AlbumPresenter{

    private AlbumView albumView;
    private GalleryRepository dataSource;

    public AlbumPresenterImpl(AlbumView albumView, GalleryRepository dataSource) {
        this.albumView = albumView;
        this.dataSource = dataSource;
    }

    public void loadImages(Album album) {
        if(album == null)
            albumView.showAlbumEmpty();
        else{
            albumView.displayLoading(true);
            load(dataSource.getImages(album));
        }
    }

    private void load(Observable<List<Image>> albums){
        albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Image>>() {
                    @Override
                    public void call(List<Image> images) {
                        albumView.displayLoading(false);
                        albumView.displayImages(images);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        albumView.displayLoading(false);
                    }
                });
    }

}
