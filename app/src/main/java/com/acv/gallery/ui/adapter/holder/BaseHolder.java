package com.acv.gallery.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acv.gallery.ui.listener.ItemClickListener;


public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener listener;

    public BaseHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    public abstract void render(T item);

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onItemClick(view, getAdapterPosition());
    }
}
