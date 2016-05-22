package com.acv.gallery.presenter;

import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.AlbumView;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumPresenterImpl implements AlbumPresenter {

    private AlbumView albumView;
    private GalleryRepository dataSource;

    public AlbumPresenterImpl(AlbumView albumView, GalleryRepository dataSource) {
        this.albumView = albumView;
        this.dataSource = dataSource;
    }

    public void loadImages(Album album) {
        if (album == null)
            albumView.showAlbumEmpty();
        else {
            albumView.displayLoading(true);
            load(dataSource.getImages(album));
        }
    }

    @Override
    public void deleteImages(List<Image> images) {
        try {
            dataSource.delete(images);
            albumView.removeImages();
            albumView.showDeleteSuccess();
        } catch (IOException e) {
            albumView.showDeleteError(e.getMessage());
        }
    }

    private void load(Observable<List<Image>> albums) {
        albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(images -> {
                    albumView.displayLoading(false);
                    albumView.displayImages(images);
                }, error -> {
                    albumView.displayLoading(false);
                });
    }

}
