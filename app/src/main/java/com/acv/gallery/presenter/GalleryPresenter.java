package com.acv.gallery.presenter;

public interface GalleryPresenter extends LifeCyclePresenter {
    void loadAlbums();

    void loadAlbumsPerDay();

    void loadAlbumsPerWeek();

    void loadAlbumsPerMonth();
}
