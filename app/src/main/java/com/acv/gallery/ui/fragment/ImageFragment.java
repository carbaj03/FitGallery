package com.acv.gallery.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acv.gallery.Album;
import com.acv.gallery.GalleryApplication;
import com.acv.gallery.Image;
import com.acv.gallery.R;
import com.acv.gallery.ui.adapter.AlbumAdapter;
import com.acv.gallery.ui.fragment.module.AlbumListFragmentModule;
import com.acv.gallery.ui.fragment.module.ImageFragmentModule;
import com.acv.gallery.ui.fragment.presenter.AlbumListFragmentPresenter;
import com.acv.gallery.ui.fragment.presenter.ImageFragmentPresenter;
import com.acv.gallery.ui.listener.ItemClickListener;
import com.acv.gallery.util.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends BaseFragment {

    public static final String TAG = ImageFragment.class.getSimpleName();

    private static final String ARG_IMAGE = "IMAGE";

    @BindView(R.id.cLProgressBar)
    ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Inject
    ImageFragmentPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    public static Fragment newInstance(Image image) {
        Fragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(getActivity()).getAppComponent()
                .plus(new ImageFragmentModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);

        ButterKnife.bind(this, rootView);

        getData();

        return rootView;
    }

    private void getData(){
        Image image = getArguments().getParcelable(ARG_IMAGE);
        presenter.loadImage(image);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gallery, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLoading(boolean loading) {
        imageView.setVisibility(loading ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setImage(Image image) {
        imageLoader.loadFull(image.getUrl(), imageView);
    }

}