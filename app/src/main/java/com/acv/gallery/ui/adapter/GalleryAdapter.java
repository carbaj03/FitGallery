package com.acv.gallery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.R;
import com.acv.gallery.Image;
import com.acv.gallery.ui.adapter.holder.GalleryViewHolder;
import com.acv.gallery.util.ImageLoader;

import java.util.List;

public class GalleryAdapter extends BaseAdapter<Image> {

    private static final int layout = R.layout.gallery_item;

    private ImageLoader imageLoader;

    public GalleryAdapter(ImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;
    }

    @Override
    public List<Image> getItems() {
        return mData;
    }

    @Override
    public Image getItem(int position) {
        return mData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateView(parent, layout);
        return new GalleryViewHolder(view, itemClickListener, imageLoader);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GalleryViewHolder gotCharacterViewHolder = (GalleryViewHolder) holder;
        gotCharacterViewHolder.render(mData.get(position));
    }
}
