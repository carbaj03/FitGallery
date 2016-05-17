package com.acv.gallery.util;


import android.widget.ImageView;

public interface ImageLoader {

    public void load(String url, ImageView imageView);

    public void loadFull(String url, ImageView imageView);
}
