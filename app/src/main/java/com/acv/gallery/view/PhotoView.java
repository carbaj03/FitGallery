package com.acv.gallery.view;


import com.acv.gallery.model.Image;

import java.util.List;

public interface PhotoView {
    void displayLoading(boolean show);
    void displayImage(Image image);

    void showImageEmpty();
}
