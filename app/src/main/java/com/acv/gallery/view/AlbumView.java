package com.acv.gallery.view;


import com.acv.gallery.model.Image;

import java.util.List;

public interface AlbumView {
    void displayLoading(boolean show);
    void displayImages(List<Image> images);

    void showAlbumEmpty();
}
