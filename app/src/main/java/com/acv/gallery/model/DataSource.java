package com.acv.gallery.model;

import com.acv.gallery.Album;
import com.acv.gallery.Image;

import java.util.List;

import rx.Observable;

public interface DataSource {

    Observable<List<Album>> getAlbums();
    Observable<List<Album>> getAlbumsPerDay();
    Observable<List<Album>> getAlbumsPerWeek();
    Observable<List<Album>> getAlbumsPerMonth();

    Observable<List<Image>> getImages(Album album);

    Observable<Image> getImage(Image image);
}
