package com.acv.gallery.view;


import com.acv.gallery.model.Album;

import java.util.List;

public interface GalleryView {
    void displayLoading(boolean show);
    void displayGallery(List<Album> albums);

    int getGroupBy();
}
