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
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.acv.gallery.GalleryApplication;
import com.acv.gallery.Image;
import com.acv.gallery.R;
import com.acv.gallery.ui.adapter.GalleryAdapter;
import com.acv.gallery.ui.fragment.module.GalleryListFragmentModule;
import com.acv.gallery.ui.fragment.presenter.GalleryListFragmentPresenter;
import com.acv.gallery.ui.listener.ItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryListFragment extends BaseFragment implements ItemClickListener {

    public static final String TAG = GalleryListFragment.class.getSimpleName();

    private static final int SUB_MENU_ALL = 11;
    private static final int SUB_MENU_PER_DAY = 21;
    private static final int SUB_MENU_PER_WEEK = 31;
    private static final int SUB_MENU_PER_MOTH = 41;

    private int vIntOrderBy = SUB_MENU_ALL;

    @BindView(R.id.cLProgressBar)
    ContentLoadingProgressBar cLProgressBar;
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    @Inject
    GalleryAdapter galleryAdapter;
    @Inject
    GalleryListFragmentPresenter presenter;

    public static Fragment newInstance() {
        return new GalleryListFragment();
    }

    @Override
    protected void setupActivityComponent() {
        GalleryApplication.get(getActivity()).getAppComponent()
                .plus(new GalleryListFragmentModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, rootView);

        galleryAdapter.setItemClickListener(this);

        getData(savedInstanceState);

        setRecyclerView();

        return rootView;
    }

    private void getData(Bundle savedInstanceState){
        presenter.loadImages();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
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
                vIntOrderBy = SUB_MENU_ALL;
                presenter.loadImages();
                return true;

            case SUB_MENU_PER_DAY:
                item.setChecked(true);
                vIntOrderBy = SUB_MENU_PER_DAY;
                presenter.loadImagesPerDay();
                return true;

            case SUB_MENU_PER_WEEK:
                item.setChecked(true);
                vIntOrderBy = SUB_MENU_PER_WEEK;
                presenter.loadImagesPerWeek();
                return true;

            case SUB_MENU_PER_MOTH:
                item.setChecked(true);
                vIntOrderBy = SUB_MENU_PER_MOTH;
                presenter.loadImagesPerMonth();
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

        if(vIntOrderBy == SUB_MENU_ALL)
            subMenu.findItem(SUB_MENU_ALL).setChecked(true);
        else if(vIntOrderBy == SUB_MENU_PER_DAY)
            subMenu.findItem(SUB_MENU_PER_DAY).setChecked(true);
        else if(vIntOrderBy == SUB_MENU_PER_WEEK)
            subMenu.findItem(SUB_MENU_PER_WEEK).setChecked(true);
        else if(vIntOrderBy == SUB_MENU_PER_MOTH)
            subMenu.findItem(SUB_MENU_PER_MOTH).setChecked(true);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    public void showLoading(boolean loading) {
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        cLProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setImages(List<Image> images) {
        galleryAdapter.setItems(images);
    }

}