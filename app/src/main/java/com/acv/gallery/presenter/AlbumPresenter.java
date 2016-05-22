package com.acv.gallery.presenter;

import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;

import java.util.List;

public interface AlbumPresenter {
    void loadImages(Album album);

    void deleteImages(List<Image> images);
}
