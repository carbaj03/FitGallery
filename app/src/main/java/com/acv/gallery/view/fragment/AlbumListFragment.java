package com.acv.gallery.view.fragment;

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
import android.widget.Toast;

import com.acv.gallery.GalleryApplication;
import com.acv.gallery.R;
import com.acv.gallery.model.Album;
import com.acv.gallery.model.Image;
import com.acv.gallery.presenter.AlbumPresenter;
import com.acv.gallery.view.AlbumView;
import com.acv.gallery.view.activity.ImageDetailActivity;
import com.acv.gallery.view.adapter.AlbumAdapter;
import com.acv.gallery.view.fragment.module.AlbumViewModule;
import com.acv.gallery.view.listener.ItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListFragment extends BaseFragment implements AlbumView, ItemClickListener {

    public static final String TAG = AlbumListFragment.class.getSimpleName();

    private static final String ARG_ALBUM = "ALBUM";

    @BindView(R.id.cLProgressBar)
    ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    @Inject
    AlbumAdapter adapter;
    @Inject
    AlbumPresenter presenter;

    private Menu menu;

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
                .plus(new AlbumViewModule(this))
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadImages(getAlbum());
    }

    private Album getAlbum() {
        return getArguments() == null ? null : getArguments().getParcelable(ARG_ALBUM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);
        adapter.setItemClickListener(this);
        setRecyclerView();
        return rootView;
    }

    private void setRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gallery, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            case R.id.action_delete:
                adapter.changeState();
                changeMenu();
                return true;

            case R.id.action_accept:
                adapter.changeState();
                accept();
                return true;

            case R.id.action_cancel:
                adapter.changeState();
                cancel();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeMenu() {
        if (adapter.isMultiSelectActive()) {
            menu.setGroupVisible(R.id.action_group_multi, true);
            menu.setGroupVisible(R.id.action_group_simple, false);
        } else {
            menu.setGroupVisible(R.id.action_group_multi, false);
            menu.setGroupVisible(R.id.action_group_simple, true);
        }
    }

    private void accept() {
        actionAccept();
        changeMenu();
    }

    private void cancel() {
        changeMenu();
    }

    private void actionAccept() {
        presenter.deleteImages(adapter.getCheckedItems());
    }

    @Override
    public void onItemClick(View view, int position) {
        if (!adapter.isMultiSelectActive())
            ImageDetailActivity.launch(getActivity(), adapter.getItems(), position);
        else
            adapter.changeState(position);
    }

    @Override
    public void displayLoading(boolean loading) {
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayImages(List<Image> images) {
        adapter.setItems(images);
    }

    @Override
    public void removeImages() {
        adapter.removeChecked();
    }

    @Override
    public void showAlbumEmpty() {
        Toast.makeText(getActivity(), "No se ha encontrado el album", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDeleteSuccess() {
        Toast.makeText(getActivity(), "Imágenes eliminadas con éxito", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDeleteError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }
}