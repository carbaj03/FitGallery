package com.acv.gallery.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.model.Image;
import com.acv.gallery.R;
import com.acv.gallery.view.adapter.holder.AlbumViewHolder;
import com.acv.gallery.util.ImageLoader;

import java.util.List;

public class AlbumAdapter extends MultiSelectAdapter<Image> {

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
        return (Image) mData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateView(parent, layout);
        return new AlbumViewHolder(view, itemClickListener, imageLoader);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        AlbumViewHolder imageViewHolder = (AlbumViewHolder) holder;

        if(isMultiSelectActive())
            imageViewHolder.render((Image) mData.get(position), isChecked(position));
        else
            imageViewHolder.render((Image) mData.get(position));
    }
}
