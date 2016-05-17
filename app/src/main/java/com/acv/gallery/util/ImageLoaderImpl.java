package com.acv.gallery.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageLoaderImpl implements ImageLoader {

    private Picasso picasso;

    public ImageLoaderImpl(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso.load(url).fit().into(imageView);
    }
}
