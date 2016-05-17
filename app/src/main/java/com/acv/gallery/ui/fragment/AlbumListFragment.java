package com.acv.gallery.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.Album;
import com.acv.gallery.GalleryApplication;
import com.acv.gallery.Image;
import com.acv.gallery.R;
import com.acv.gallery.ui.adapter.AlbumAdapter;
import com.acv.gallery.ui.fragment.module.AlbumListFragmentModule;
import com.acv.gallery.ui.fragment.presenter.AlbumListFragmentPresenter;
import com.acv.gallery.ui.listener.ItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListFragment extends BaseFragment implements ItemClickListener {

    public static final String TAG = AlbumListFragment.class.getSimpleName();

    private static final String ARG_ALBUM = "ALBUM";

    @BindView(R.id.cLProgressBar)
    ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    @Inject
    AlbumAdapter albumAdapter;
    @Inject
    AlbumListFragmentPresenter presenter;

    public static Fragment newInstance(Album album) {
        Fragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ALBUM, album);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(getActivity()).getAppComponent()
                .plus(new AlbumListFragmentModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, rootView);

        albumAdapter.setItemClickListener(this);

        getData();

        setRecyclerView();

        return rootView;
    }

    private void getData(){
        Album album = getArguments().getParcelable(ARG_ALBUM);
        presenter.loadImages(album);
    }

    private void setRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(albumAdapter);
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

    @Override
    public void onItemClick(View view, int position) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ImageFragment.newInstance(albumAdapter.getItem(position)), ImageFragment.TAG)
                .addToBackStack(ImageFragment.TAG)
                .commit();
    }

    public void showLoading(boolean loading) {
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setImages(List<Image> images) {
        albumAdapter.setItems(images);
    }

}