package com.acv.gallery.util;

import android.view.View;
import android.widget.ImageView;

import com.acv.gallery.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class GlideImageLoader implements ImageLoader {

    @Override
    public void load(String url, ImageView imageView) {
        Glide.with(imageView.getContext()).load(url).error(R.drawable.ic_photo_camera_white_24dp)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadFull(String url, ImageView imageView, View parent) {
        Glide.with(imageView.getContext()).load(url).error(R.drawable.ic_photo_camera_white_24dp)
                .into(imageView);
    }
}

