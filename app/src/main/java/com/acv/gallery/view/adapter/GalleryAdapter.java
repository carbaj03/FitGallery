package com.acv.gallery.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.model.Album;
import com.acv.gallery.R;
import com.acv.gallery.view.adapter.holder.GalleryViewHolder;
import com.acv.gallery.util.ImageLoader;

import java.util.List;

public class GalleryAdapter extends BaseAdapter<Album> {

    private static final int layout = R.layout.album_item;

    private ImageLoader imageLoader;

    public GalleryAdapter(ImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;
    }

    @Override
    public List<Album> getItems() {
        return mData;
    }

    @Override
    public Album getItem(int position) {
        return mData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateView(parent, layout);
        return new GalleryViewHolder(view, itemClickListener, imageLoader);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GalleryViewHolder albumViewHolder = (GalleryViewHolder) holder;
        albumViewHolder.render(mData.get(position));
    }
}
