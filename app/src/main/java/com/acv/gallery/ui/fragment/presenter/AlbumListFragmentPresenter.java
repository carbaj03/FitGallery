package com.acv.gallery.ui.fragment.presenter;

import com.acv.gallery.Album;
import com.acv.gallery.Image;
import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.fragment.AlbumListFragment;
import com.acv.gallery.ui.fragment.GalleryListFragment;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AlbumListFragmentPresenter {

    private AlbumListFragment albumListFragment;
    private DataSource dataSource;

    public AlbumListFragmentPresenter(AlbumListFragment albumListFragment, DataSource dataSource) {
        this.albumListFragment = albumListFragment;
        this.dataSource = dataSource;
    }

    public void loadImages(Album album) {
        albumListFragment.showLoading(true);
        load(dataSource.getImages(album));
    }

    private void load(Observable<List<Image>> albums){
        albums.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Image>>() {
                    @Override
                    public void call(List<Image> images) {
                        albumListFragment.showLoading(false);
                        albumListFragment.setImages(images);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        albumListFragment.showLoading(false);
                    }
                });
    }

}
