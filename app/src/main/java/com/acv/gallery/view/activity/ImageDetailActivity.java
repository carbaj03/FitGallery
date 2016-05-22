/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acv.gallery.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.acv.gallery.GalleryApplication;
import com.acv.gallery.R;
import com.acv.gallery.model.Image;
import com.acv.gallery.view.activity.module.ImageDetailModule;
import com.acv.gallery.view.adapter.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailActivity extends BaseActivity {

    public static final String EXTRA_IMAGES = "extra_images";
    public static final String EXTRA_CURRENT_POSITION = "extra_current_position";

    public static final int OFFSCREEN_PAGE_LIMIT = 2;
    public static final int EMPTY_CURRENT_POSITION = -1;

    @BindView(R.id.pager) ViewPager pager;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private ImagePagerAdapter adapter;

    private int currentPosition;

    public static void launch(Activity activity, List<Image> images, int currentPosition) {
        activity.startActivity(newInstance(activity, images, currentPosition));
    }

    private static Intent newInstance(Context context, List<Image> images, int currentPosition) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_IMAGES, (ArrayList<Image>) images);
        intent.putExtra(EXTRA_CURRENT_POSITION, currentPosition);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);

        ButterKnife.bind(this);

        setToolbar(toolbar);
        setImagePager();
        setCurrentPosition();

    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(this).getAppComponent()
                .plus(new ImageDetailModule(this))
                .inject(this);
    }

    private void setImagePager() {
        adapter = new ImagePagerAdapter(getSupportFragmentManager(), getImages());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
    }

    private List<Image> getImages(){
        return getIntent().getExtras().getParcelableArrayList(EXTRA_IMAGES) == null ?
                new ArrayList<>() :
                getIntent().getExtras().getParcelableArrayList(EXTRA_IMAGES);
    }

    private void setCurrentPosition() {
        currentPosition = getIntent().getIntExtra(EXTRA_CURRENT_POSITION, EMPTY_CURRENT_POSITION);
        if (currentPosition != EMPTY_CURRENT_POSITION) {
            pager.setCurrentItem(currentPosition);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
