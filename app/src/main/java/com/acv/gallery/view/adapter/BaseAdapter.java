package com.acv.gallery.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.view.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final List<T> mData;

    protected ItemClickListener itemClickListener;

    public BaseAdapter() {
        mData = new ArrayList<T>();
    }

    public void setItemClickListener(ItemClickListener listener){
        itemClickListener = listener;
    }

    public void setItems(Collection<T> data) {
        if ((data == null) || (data.isEmpty()))
            return;

        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract List<T> getItems();

    public abstract T getItem(int position);

    protected View inflateView(ViewGroup parent, int layout){
        return LayoutInflater.from(
                parent.getContext()).inflate(layout, parent, false);
    }

}
