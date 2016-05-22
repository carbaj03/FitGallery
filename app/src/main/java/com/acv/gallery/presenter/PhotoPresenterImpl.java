package com.acv.gallery.presenter;

import com.acv.gallery.model.Image;
import com.acv.gallery.repository.GalleryRepository;
import com.acv.gallery.view.PhotoView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PhotoPresenterImpl implements PhotoPresenter {

    private PhotoView photoView;
    private GalleryRepository dataSource;
    private Subscription subscription;

    public PhotoPresenterImpl(PhotoView photoView, GalleryRepository dataSource) {
        this.photoView = photoView;
        this.dataSource = dataSource;
    }

    public void loadImage(Image image) {
        photoView.displayLoading(true);
        load(dataSource.getImage(image));
    }

    private void load(Observable<Image> image){
        subscription = image.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(image1 -> {
                    photoView.displayLoading(false);
                    photoView.displayImage(image1);
                }, error -> {
                    photoView.displayLoading(false);
                });
    }

}
