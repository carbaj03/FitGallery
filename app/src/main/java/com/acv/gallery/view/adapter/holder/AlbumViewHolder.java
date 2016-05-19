package com.acv.gallery.view.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acv.gallery.model.Image;
import com.acv.gallery.R;
import com.acv.gallery.view.listener.ItemClickListener;
import com.acv.gallery.util.ImageLoader;


public class AlbumViewHolder extends BaseHolder<Image> {

    private ImageView ivBackground;
    private TextView tvName;
    private ImageLoader imageLoader;

    public AlbumViewHolder(View itemView, ItemClickListener listener, ImageLoader imageLoader) {
        super(itemView, listener);
        ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        itemView.setOnClickListener(this);
        this.imageLoader = imageLoader;
    }

    public void render(Image image) {
        tvName.setText(image.getDateFormat());
        imageLoader.load(image.getUrl(), ivBackground);
    }
}