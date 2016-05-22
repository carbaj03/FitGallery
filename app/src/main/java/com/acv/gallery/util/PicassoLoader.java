package com.acv.gallery.util;

import android.view.View;
import android.widget.ImageView;

import com.acv.gallery.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

public class PicassoLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso.load(url).fit().centerInside().into(imageView);
    }

    @Override
    public void loadFull(String url, ImageView imageView, View parent) {
        picasso.load(url).error(R.drawable.ic_photo_camera_white_24dp)
//                .fit()
                .resize(parent.getWidth(),parent.getHeight())
                .centerInside()
                .into(imageView);
    }
}

