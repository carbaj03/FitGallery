package com.acv.gallery.ui.fragment.presenter;

import com.acv.gallery.Image;
import com.acv.gallery.model.DataSource;
import com.acv.gallery.ui.fragment.ImageFragment;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ImageFragmentPresenter {

    private ImageFragment imageFragment;
    private DataSource dataSource;

    public ImageFragmentPresenter(ImageFragment imageFragment, DataSource dataSource) {
        this.imageFragment = imageFragment;
        this.dataSource = dataSource;
    }

    public void loadImage(Image image) {
        imageFragment.showLoading(true);
        load(dataSource.getImage(image));
    }

    private void load(Observable<Image> image){
        image.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Image>() {
                    @Override
                    public void call(Image image) {
                        imageFragment.showLoading(false);
                        imageFragment.setImage(image);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        imageFragment.showLoading(false);
                    }
                });
    }

}
