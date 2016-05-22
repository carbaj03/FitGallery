package com.acv.gallery.view.adapter;


import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class MultiSelectAdapter<T> extends BaseAdapter {

    protected SparseBooleanArray itemsChecked;

    protected boolean active = false;

    public MultiSelectAdapter() {
        super();
        itemsChecked = new SparseBooleanArray();
    }

    @Override
    public void setItems(Collection data) {
        super.setItems(data);
        initializeChecked();
    }

    public void initializeChecked(){
        int max = getItemCount();
        for (int position = 0; position < max; position++) {
            itemsChecked.append(position, false);
        }
    }

    public void changeState() {
        this.active = !active;
        notifyDataSetChanged();
    }

    public boolean isMultiSelectActive(){
        return active;
    }

    public List<T> getCheckedItems(){
        List<T> items = new ArrayList<>();
        int max = itemsChecked.size();
        for (int position = 0; position < max; position++) {
            if(itemsChecked.valueAt(position))
                items.add((T) mData.get(position));
        }
        return items;
    }

    public void removeChecked(){
        int max = itemsChecked.size() - 1;
        for (int position = max; position >= 0; position--) {
            if(itemsChecked.valueAt(position)){
                mData.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void changeState(int position){
        itemsChecked.put(position, !itemsChecked.valueAt(position));
        notifyItemChanged(position);
    }

    public boolean isChecked(int position){
        return itemsChecked.valueAt(position);
    }

}
