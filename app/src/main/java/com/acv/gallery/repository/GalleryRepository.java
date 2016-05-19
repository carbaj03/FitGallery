package com.acv.gallery.repository;

import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;

import java.util.List;

import rx.Observable;

public interface GalleryRepository {

    Observable<List<Album>> getAlbums();
    Observable<List<Album>> getAlbumsPerDay();
    Observable<List<Album>> getAlbumsPerWeek();
    Observable<List<Album>> getAlbumsPerMonth();

    Observable<List<Image>> getImages(Album album);

    Observable<Image> getImage(Image image);
}
