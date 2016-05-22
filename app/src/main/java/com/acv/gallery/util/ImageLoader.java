package com.acv.gallery.util;


import android.view.View;
import android.widget.ImageView;

public interface ImageLoader {

    public void load(String url, ImageView imageView);

    public void loadFull(String url, ImageView imageView, View view);
}
