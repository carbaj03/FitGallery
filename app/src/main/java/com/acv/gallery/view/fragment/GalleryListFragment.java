package com.acv.gallery.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.GalleryApplication;
import com.acv.gallery.R;
import com.acv.gallery.model.Album;
import com.acv.gallery.presenter.GalleryPresenter;
import com.acv.gallery.view.GalleryView;
import com.acv.gallery.view.adapter.GalleryAdapter;
import com.acv.gallery.view.fragment.module.GalleryViewModule;
import com.acv.gallery.view.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryListFragment extends BaseFragment implements GalleryView, ItemClickListener {

    public static final String TAG = GalleryListFragment.class.getSimpleName();

    private static final String ARG_GALLERY = "GALLERY";

    public static final int SUB_MENU_ALL = 11;
    public static final int SUB_MENU_PER_DAY = 21;
    public static final int SUB_MENU_PER_WEEK = 31;
    public static final int SUB_MENU_PER_MOTH = 41;

    private int groupBy = SUB_MENU_ALL;

    @BindView(R.id.cLProgressBar)
    ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    @Inject
    GalleryAdapter galleryAdapter;
    @Inject
    GalleryPresenter presenter;

    public static Fragment newInstance() {
        return new GalleryListFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_GALLERY, (ArrayList<Album>)galleryAdapter.getItems());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadAlbums();
    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(getActivity()).getAppComponent()
                .plus(new GalleryViewModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);
        galleryAdapter.setItemClickListener(this);
        setRecyclerView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(galleryAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gallery, menu);
        setMenu(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

            return true;

            case SUB_MENU_ALL:
                item.setChecked(true);
                groupBy = item.getItemId();
                presenter.loadAlbums();
                return true;
            case SUB_MENU_PER_DAY:
                item.setChecked(true);
                groupBy = item.getItemId();
                presenter.loadAlbums();
                return true;
            case SUB_MENU_PER_WEEK:
                item.setChecked(true);
                groupBy = item.getItemId();
                presenter.loadAlbums();
                return true;
            case SUB_MENU_PER_MOTH:
                item.setChecked(true);
                groupBy = item.getItemId();
                presenter.loadAlbums();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMenu(Menu menu){
        SubMenu subMenu = menu.findItem(R.id.action_order).getSubMenu();
        subMenu.addSubMenu(R.id.group_order, SUB_MENU_ALL, 100, getString(R.string.action_order_all));
        subMenu.addSubMenu(R.id.group_order, SUB_MENU_PER_DAY, 101, getString(R.string.action_order_per_day));
        subMenu.addSubMenu(R.id.group_order, SUB_MENU_PER_WEEK, 102, getString(R.string.action_order_per_week));
        subMenu.addSubMenu(R.id.group_order, SUB_MENU_PER_MOTH, 103, getString(R.string.action_order_per_month));
        subMenu.setGroupCheckable(R.id.group_order, true, true);
        subMenu.findItem(groupBy).setChecked(true);
    }

    @Override
    public void onItemClick(View view, int position) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AlbumListFragment.newInstance(galleryAdapter.getItem(position)), AlbumListFragment.TAG)
                .addToBackStack(AlbumListFragment.TAG)
                .commit();
    }

    public void displayLoading(boolean loading) {
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void displayGallery(List<Album> albums) {
        galleryAdapter.setItems(albums);
    }

    @Override
    public int getGroupBy() {
        return groupBy;
    }

}