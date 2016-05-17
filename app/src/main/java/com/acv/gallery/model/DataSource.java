package com.acv.gallery.model;

import com.acv.gallery.Album;
import com.acv.gallery.Image;

import java.util.List;

import rx.Observable;

public interface DataSource {

    Observable<List<Image>> getImages();
    Observable<List<Image>> getImagesPerDay();
    Observable<List<Image>> getImagesPerWeek();
    Observable<List<Image>> getImagesPerMonth();

}
