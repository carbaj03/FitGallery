package com.acv.gallery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.Image;
import com.acv.gallery.R;
import com.acv.gallery.ui.adapter.holder.AlbumViewHolder;
import com.acv.gallery.util.ImageLoader;

import java.util.Date;
import java.util.List;

public class AlbumAdapter extends BaseAdapter<Image> {

    private static final int layout = R.layout.image_item;

    private ImageLoader imageLoader;

    public AlbumAdapter(ImageLoader imageLoader) {
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
        return new AlbumViewHolder(view, itemClickListener, imageLoader);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        AlbumViewHolder imageViewHolder = (AlbumViewHolder) holder;
        imageViewHolder.render(mData.get(position));
    }
}
