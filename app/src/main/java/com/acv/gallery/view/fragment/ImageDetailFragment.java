package com.acv.gallery.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.acv.gallery.GalleryApplication;
import com.acv.gallery.R;
import com.acv.gallery.model.Image;
import com.acv.gallery.presenter.PhotoPresenter;
import com.acv.gallery.util.ImageLoader;
import com.acv.gallery.view.PhotoView;
import com.acv.gallery.view.fragment.module.PhotoViewModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailFragment extends BaseFragment implements PhotoView {

    public static final String TAG = ImageDetailFragment.class.getSimpleName();

    private static final String ARG_IMAGE = "IMAGE";

    @BindView(R.id.cLProgressBar) ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.layout_parent) View viewParent;

    @Inject PhotoPresenter presenter;
    @Inject ImageLoader imageLoader;

    public static Fragment newInstance(Image image) {
        Fragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(getActivity()).getAppComponent()
                .plus(new PhotoViewModule(this))
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadImage(getImage());
    }

    private Image getImage(){
        return getArguments() == null ? null : getArguments().getParcelable(ARG_IMAGE);
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
        inflater.inflate(R.menu.image_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case R.id.action_delete:

            return true;
        case R.id.action_compare:

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayLoading(boolean show) {
        imageView.setVisibility(show ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayImage(Image image) {
        imageLoader.loadFull(image.getUrl(), imageView, viewParent);
    }

    @Override
    public void showImageEmpty() {
        Toast.makeText(getActivity(), "No se ha encontrado la imagen", Toast.LENGTH_SHORT).show();
    }
}