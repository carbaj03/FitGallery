package com.acv.gallery.util;

import android.widget.ImageView;

import com.acv.gallery.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

public class ImageLoaderImpl implements ImageLoader {

    private Picasso picasso;

    public ImageLoaderImpl(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso.load(url).fit().centerCrop().into(imageView);
    }

    @Override
    public void loadFull(String url, ImageView imageView) {
        picasso.load(url).error(R.drawable.ic_photo_camera_white_24dp).fit().centerInside().into(imageView);
    }
}

