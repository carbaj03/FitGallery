package com.acv.gallery.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.acv.gallery.model.Image;
import com.acv.gallery.view.fragment.ImageDetailFragment;

import java.util.List;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Image> images;

    public ImagePagerAdapter(FragmentManager fm, List<Image> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ImageDetailFragment.newInstance(images.get(position));
    }
}


