package com.acv.gallery.view.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acv.gallery.model.Album;
import com.acv.gallery.R;
import com.acv.gallery.view.listener.ItemClickListener;
import com.acv.gallery.util.ImageLoader;


public class GalleryViewHolder extends BaseHolder<Album> {

    private ImageView ivBackground;
    private TextView tvName;
    private ImageLoader imageLoader;

    public GalleryViewHolder(View itemView, ItemClickListener listener, ImageLoader imageLoader) {
        super(itemView, listener);
        ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        itemView.setOnClickListener(this);
        this.imageLoader = imageLoader;
    }

    public void render(Album album) {
        tvName.setText(album.getDateFormat());
        imageLoader.load(album.getUrl(), ivBackground);
    }
}